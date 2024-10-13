use axum::{
    extract::{ Path, State},
    http::StatusCode,
    routing::{get, patch},
    Json, Router,
};
use serde::{Deserialize, Serialize};
use serde_json::json;
use sqlx::{postgres::PgPoolOptions, PgPool};
use tokio::net::TcpListener;

#[tokio::main]
async fn main() {
    //expose env var
    dotenv::dotenv().expect("unable to access env file !");


    //set env var
    let server_addr = std::env::var("SERVER_ADDRESS").unwrap_or("127.0.0.1:8080".to_owned());
    let database_url = std::env::var("DATABASE_URL").expect("DATABASE_URL must be set");

    //create db pool connection
    let db_pool = PgPoolOptions::new()
        .max_connections(16)
        .connect(&database_url)
        .await
        .expect("can't connect to database");

    //create tcp listener
    let listener = TcpListener::bind(server_addr)
        .await
        .expect("can't bind to server port");

    println!("listening on {}", listener.local_addr().unwrap());

    //compose routes
    let app = Router::new().route("/",get(||async { "Hello, world!" }))
        .route("/foods",
               get(get_food)
                   .post(create_food)
        )
        .route("/food/:food_id",
               patch(update_food)
                   .delete(delete_food))
        .with_state(db_pool);

    //serve apps
    axum::serve(listener, app).await.expect("can't start server");

}

#[derive(Serialize, Deserialize)]
struct FoodRows{
    id: i32,
    name: String,
    description: Option<String>,
}

async fn get_food(State(pg_pool):State<PgPool>,) -> Result<(StatusCode, String), (StatusCode, String)> {
    let rows = sqlx::query_as!(FoodRows,"SELECT * FROM indonesia_food")
        .fetch_all(&pg_pool)
        .await
        .map_err(|e| (
            StatusCode::INTERNAL_SERVER_ERROR,
            json!({
                "status":"false",
                "message":e.to_string()}).to_string()
        ))?;

    Ok((
        StatusCode::OK,
        json!({
            "status":"ok",
            "data": rows,
        }).to_string()
        ))
}

#[derive(Serialize, Deserialize)]
struct CreateFoodReq{
    name: String,
    description: String,
}

#[derive(Serialize)]
struct CreatFoodRow{
    id: i32
}
async fn create_food(State(pg_pool):State<PgPool>,
Json(food): Json<CreateFoodReq>) -> Result<(StatusCode, String), (StatusCode, String)> {
    let row = sqlx::query_as!(CreatFoodRow, "INSERT INTO indonesia_food (name, description) VALUES ($1, $2) RETURNING id",
        food.name,food.description
    ).fetch_one(&pg_pool)
        .await
        .map_err(|e| (
            StatusCode::INTERNAL_SERVER_ERROR,
            json!({
                "status":"false",
                "message":e.to_string()}).to_string()
        ))?;

    Ok((
        StatusCode::CREATED,
        json!({
            "status":"ok",
            "data": row,
        }).to_string()
    ))
}

async fn update_food(State(pg_pool):State<PgPool>,
Path(id): Path<i32>, Json(food): Json<CreateFoodReq>) -> Result<(StatusCode, String), (StatusCode, String)> {
    let _row = sqlx::query!("UPDATE indonesia_food SET name=$2, description=$3 WHERE id=$1",
    id, food.name, food.description)
        .execute(&pg_pool)
        .await
        .map_err(|e| {
            (StatusCode::INTERNAL_SERVER_ERROR,
            json!({
                "status":"false",
                "message":e.to_string()}).to_string(),
        )})?;

    Ok((
        StatusCode::OK,
        json!({
            "status":"ok",
            "message":"successfully updated food",
            "data": id,
        }).to_string()
    ))

}
async fn delete_food(State(pg_pool):State<PgPool>,
Path(id): Path<i32>) -> Result<(StatusCode, String), (StatusCode, String)> {
    let _result = sqlx::query!("DELETE FROM indonesia_food where id=$1", id)
        .execute(&pg_pool)
        .await
        .map_err(|e| {
            (StatusCode::INTERNAL_SERVER_ERROR,
             json!({
                "status":"false",
                "message":e.to_string()}).to_string(),
            )})?;
    Ok((
        StatusCode::OK,
        json!({
            "status":"ok",
            "message":"successfully delete food",
            "data": id,
        }).to_string()
    ))
}
