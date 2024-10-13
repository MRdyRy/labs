mod model;

use actix_web::{web, App, HttpResponse, HttpServer, Responder};
use sqlx::postgres::PgPoolOptions;
use dotenv::dotenv;
use actix_web::web::Data;
use std::env;
use log::info;

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    dotenv().ok();
    env_logger::init();
    let db_url = env::var("DATABASE_URL").expect("DATABASE_URL is not set in .env file");

    info!("Using DATABASE_URL: {}", &db_url);
    let pool = PgPoolOptions::new()
        .max_connections(5)
        .connect(&db_url)
        .await
        .expect("error creating pool");

    HttpServer::new(move || {
        App::new()
            .app_data(Data::new(pool.clone()))
            .route("/foods", web::post().to(create_food))
            // .route("/food/{ids}", web::get().to(get_food))
            // .route("/food/{ids}", web::put().to(put_food))
            // .route("/food/{ids}", web::delete().to(delete_food))
    })
        .bind(("0.0.0.0", 8080))?
        .run()
        .await
}


async fn create_food(pool: web::Data<sqlx::PgPool>, food: web::Json<model::Food>) -> impl Responder {
    let result = sqlx::query("Insert into food (id, name, description, price) Values ($1,$2,$3,$4) Returning id")
        .bind(&food.name)
        .bind(&food.description)
        .fetch_one(pool.get_ref())
        .await;

    match result {
        Ok(record) => HttpResponse::Created().finish(),

        Err(_) => HttpResponse::InternalServerError().finish(),
    }

}

async fn get_food(pool: web::Data<sqlx::PgPool>, web::Path(ids): web::Path<i32>) -> impl Responder {
    let result = sqlx::query_as::<_,model::Food>("SELECT * FROM food WHERE id = $1")
        .bind(ids)
        .fetch_one(pool.get_ref())
        .await;
    match result {
        Ok(food) => HttpResponse::Ok().json(serde_json::json!({
            "status":"200",
            "message":"ok",
            "data": food

        })),
        Err(_) => HttpResponse::NotFound().finish(),
    }
}

async fn put_food (pool: web::Data<sqlx::PgPool>, web::Path(ids): web::Path<i32>, food: web::Json<model::Food>) -> impl Responder {
    let result = sqlx::query("update food set name = $1, description = $2, price = $3 where id = $4")
        .bind(&food.name)
        .bind(&food.description)
        .bind(&food.price)
        .bind(ids)
        .execute(pool.get_ref())
        .await;

    match result {
        Ok(_) => HttpResponse::Ok().finish(),
        Err(_) => HttpResponse::InternalServerError().finish(),
    }
}


async fn delete_food (pool: web::Data<sqlx::PgPool>, web::Path(ids): web::Path<i32>) -> impl Responder {
    let result = sqlx::query("delete from food where id = $1")
        .bind(ids)
        .execute(pool.get_ref())
        .await;

    match result {
        Ok(_) => HttpResponse::Ok().finish(),
        Err(_) => HttpResponse::InternalServerError().finish(),
    }
}