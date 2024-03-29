/*{
"info": {
"_postman_id": "fb838192-39d1-491d-93e3-c717c39d844c",
"name": "EcommerceAPI",
"schema": "http://schema.getpostman.com/json/collection/v2.1.0/collection.json"
},

"item": [
{
"name": "Authorization",
"item": [
{
"name": "Signup a new user",
"request": {
"method": "POST",
"header": [
{
"key": "Content-Type",
"name": "Content-Type",
"value": "application/json",
"type": "text"
},
{
"key": "X-Requested-With",
"value": "XMLhttpRequest",
"type": "text"
}
],
"body": {
"mode": "raw",
"raw": "{\n\t\"name\": \"\",\n\t\"email\": \"\",\n\t\"password\": \"\",\n\ t\"passowrd_confirmation\": \"\"\n}"
},
"url": {
"raw": "{{baseurl}}/auth/signup",
"host": [
"{{baseurl}}"
],
"path": [
"auth",
"signup"
]
}
},
"response": []
},
{
"name": "Signin an user",
"request": {
"method": "POST",
"header": [
{
"key": "Content-Type",
"name": "Content-Type",
"value": "application/json",
"type": "text"
},
{
"key": "X-Requested-With",
"type": "text",
"value": "XMLhttpRequest"
}
],
"body": {
"mode": "raw",
"raw": "{\n\t\"email\": \"admin@example.com\",\n\t\"password\": \"123456\",\n\ t\"remember_me\": false\n}"
},
"url": {
"raw": "{{baseurl}}/auth/login",
"host": [
"{{baseurl}}"
],
"path": [
"auth",
"login"
]
}
},
"response": []
},
{
"name": "Logout from session",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "GET",
"header": [
{
"key": "Authorization",
"value": "Bearer {{auth-token}}",
"type": "text"
}
],
"body": {
"mode": "raw",
"raw": "{\n\t\"email\": \"admin@example.com\",\n\t\"password\": \"123456\",\n\ t\"remember_me\": false\n}"
},
"url": {
"raw": "{{baseurl}}/auth/logout",
"host": [
"{{baseurl}}"
],
"path": [
"auth",
"logout"
]
}
},
"response": []
}
]
},
{
"name": "Brand",
"item": [
{
"name": "Get all brands",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/brands",
"host": [
"{{baseurl}}"
],
"path": [
"brands"
]
}
},
"response": []
},
{
"name": "Get top brands",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/brands/top",
"host": [
"{{baseurl}}"
],
"path": [
"brands",
"top"
]
}
},
"response": []
}
]
},
{
"name": "Category",
"item": [
{
"name": "Get all categories",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/categories",
"host": [
"{{baseurl}}"
],
"path": [
"categories"
]
}
},
"response": []
},
{
"name": "Get featured categories",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/categories/featured",
"host": [
"{{baseurl}}"
],
"path": [
"categories",
"featured"
]
}
},
"response": []
},
{
"name": "Get top categories",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/categories/top",
"host": [
"{{baseurl}}"
],
"path": [
"categories",
"top"
]
}
},
"response": []
},
{
"name": "Get home categories",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/home-categories",
"host": [
"{{baseurl}}"
],
"path": [
"home-categories"
]
}
},
"response": []
},
{
"name": "Get sub categories of category",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/sub-categories/1",
"host": [
"{{baseurl}}"
],
"path": [
"sub-categories",
"1"
]
}
},
"response": []
}
]
},
{
"name": "Product",
"item": [
{
"name": "Get all products (paginated)",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products?page=1",
"host": [
"{{baseurl}}"
],
"path": [
"products"
],
"query": [
{
"key": "page",
"value": "1"
}
]
}
},
"response": []
},
{
"name": "Get all products of admin (paginated)",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/admin?page=1",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"admin"
],
"query": [
{
"key": "page",
"value": "1"
}
]
}
},
"response": []
},
{
"name": "Get all products of seller (paginated)",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/seller?page=1",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"seller"
],
"query": [
{
"key": "page",
"value": "1"
}
]
}
},
"response": []
},
{
"name": "Get all products of category (paginated)",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/category/1?page=1",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"category",
"1"
],
"query": [
{
"key": "page",
"value": "1"
},
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
},
{
"name": "Get all products of sub category (paginated)",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/sub-category/1?page=1",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"sub-category",
"1"
],
"query": [
{
"key": "page",
"value": "1"
},
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
},
{
"name": "Get all products of brand (paginated)",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/brand/66?page=1",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"brand",
"66"
],
"query": [
{
"key": "page",
"value": "1"
},
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
},
{
"name": "Get all products of todays deal",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/todays-deal",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"todays-deal"
],
"query": [
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
},
{
"name": "Get all featured products",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/featured",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"featured"
],
"query": [
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
},
{
"name": "Get related products of product",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/related/1",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"related",
"1"
]
}
},
"response": []
},
{
"name": "Get top selling products from seller",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/related/1",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"related",
"1"
]
}
},
"response": []
},
{
"name": "Get all best selling products",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/best-seller",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"best-seller"
],
"query": [
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
},
{
"name": "Get all featured products",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/best-seller",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"best-seller"
],
"query": [
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
},
{
"name": "Get details of product",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/products/1",
"host": [
"{{baseurl}}"
],
"path": [
"products",
"1"
]
}
},
"response": []
}
]
},
{
"name": "Wishlist",
"item": [
{
"name": "Get wishlist of user",
"request": {
"method": "GET",
"header": [
{
"key": "Authorization",
"value": "Bearer {{auth-token}}",
"type": "text"
}
],
"url": {
"raw": "{{baseurl}}/wishlists/38",
"host": [
"{{baseurl}}"
],
"path": [
"wishlists",
"38"
],
"query": [
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
},
{
"name": "Save to wishlist of user",
"request": {
"method": "POST",
"header": [
{
"key": "Content-Type",
"name": "Content-Type",
"value": "application/json",
"type": "text"
},
{
"key": "X-Requested-With",
"value": "XMLhttpRequest",
"type": "text"
},
{
"key": "Authorization",
"value": "Bearer {{auth-token}}",
"type": "text"
}
],
"body": {
"mode": "raw",
"raw": "{\n\t\"user_id\": \"\",\n\t\"product_id\": \"\"\n}"
},
"url": {
"raw": "{{baseurl}}/wishlists",
"host": [
"{{baseurl}}"
],
"path": [
"wishlists"
]
}
},
"response": []
},
{
"name": "Remove from wishlist of user",
"request": {
"method": "DELETE",
"header": [
{
"key": "Authorization",
"type": "text",
"value": "Bearer {{auth-token}}"
}
],
"body": {
"mode": "urlencoded",
"urlencoded": []
},
"url": {
"raw": "{{baseurl}}/wishlists/1",
"host": [
"{{baseurl}}"
],
"path": [
"wishlists",
"1"
]
}
},
"response": []
}
]
},
{
"name": "Purchase History",
"item": [
{
"name": "Get purchase history of user",
"request": {
"method": "GET",
"header": [
{
"key": "Authorization",
"value": "Bearer {{auth-token}}",
"type": "text"
}
],
"url": {
"raw": "{{baseurl}}/purchase-history/1",
"host": [
"{{baseurl}}"
],
"path": [
"purchase-history",
"1"
]
}
},
"response": []
},
{
"name": "Get purchase history detail of purchase",
"request": {
"method": "GET",
"header": [
{
"key": "Authorization",
"type": "text",
"value": "Bearer {{auth-token}}"
}
],
"url": {
"raw": "{{baseurl}}/purchase-history-details/37",
"host": [
"{{baseurl}}"
],
"path": [
"purchase-history-details",
"37"
]
}
},
"response": []
}
]
},
{
"name": "Review",
"item": [
{
"name": "Get all reviews of product",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/reviews/product/27",
"host": [
"{{baseurl}}"
],
"path": [
"reviews",
"product",
"27"
]
}
},
"response": []
}
]
},
{
"name": "Get all banners",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/banners",
"host": [
"{{baseurl}}"
],
"path": [
"banners"
]
}
},
"response": []
},
{
"name": "Get business settings",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/business-settings",
"host": [
"{{baseurl}}"
],
"path": [
"business-settings"
]
}
},
"response": []
},
{
"name": "Get all colors",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/colors",
"host": [
"{{baseurl}}"
],
"path": [
"colors"
]
}
},
"response": []
},
{
"name": "Get all currencies",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/currencies",
"host": [
"{{baseurl}}"
],
"path": [
"currencies"
]
}
},
"response": []
},
{
"name": "Get general settings",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/general-settings",
"host": [
"{{baseurl}}"
],
"path": [
"general-settings"
]
}
},
"response": []
},
{
"name": "Get all shops",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/shops",
"host": [
"{{baseurl}}"
],
"path": [
"shops"
],
"query": [
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
},
{
"name": "Get shop of user",
"request": {
"auth": {
"type": "noauth"
},
"method": "GET",
"header": [
{
"key": "Authorization",
"value": "Bearer {{auth-token}}",
"type": "text"
}
],
"url": {
"raw": "{{baseurl}}/shop/user/1",
"host": [
"{{baseurl}}"
],
"path": [
"shop",
"user",
"1"
],
"query": [
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
},
{
"name": "Get all sliders",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/sliders",
"host": [
"{{baseurl}}"
],
"path": [
"sliders"
],
"query": [
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
},
{
"name": "Get app settings",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "{{baseurl}}/settings",
"host": [
"{{baseurl}}"
],
"path": [
"settings"
],
"query": [
{
"key": "",
"value": "",
"disabled": true
}
]
}
},
"response": []
}
],
"event": [
{
"listen": "prerequest",
"script": {
"id": "0791a039-2ac0-49a5-b017-071a8aaf86f9",
"type": "text/javascript",
"exec": [
""
]
}
},
{
"listen": "test",
"script": {
"id": "f260a573-9f6e-43b6-a1f4-bcb2fd252344",
"type": "text/javascript",
"exec": [
""
]
}
}
],
"variable": [
{
"id": "60b48e77-44ae-4f0f-8108-0a1b1fda31b4",
"key": "baseurl",
"value": "http://localhost/activeit/ecommerce_api/public/api/v1",
"type": "string"
},
{
"id": "3a0f2862-49d2-4720-b9d8-8250e621289c",
"key": "auth-token",
"value": "somevalue",
"type": "string"
}
]
}
*/
