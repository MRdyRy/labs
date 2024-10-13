use serde::{Deserialize, Serialize};
use sqlx::FromRow;

#[derive(Serialize, Deserialize, FromRow, Clone, Debug)]
pub struct Food {
    pub id: i32,
    pub name: String,
    pub description: String,
 }