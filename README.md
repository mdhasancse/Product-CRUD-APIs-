# Product-CRUD-APIs-

Create a Product
Endpoint: POST /products
Response:
201 Created: Product created successfully.
400 Bad Request: Invalid request body.

Get All Products
Endpoint: GET /products
Description: Get a list of all products.
Response:
200 OK: List of products retrieved successfully.

Get a Product by ID
Endpoint: GET /products/{id}
Description: Get a specific product by its ID.
Response:
200 OK: Product retrieved successfully.
404 Not Found: Product with the specified ID not found.

Update a Product
Endpoint: PUT /products/{id}
Description: Update an existing product.
