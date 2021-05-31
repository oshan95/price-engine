price-engine-frontend repo url: https://github.com/oshan95/price-engine-frontend

Prerequisites:

    1) This application uses a PostgresSql database
        *) Database name: enginedb
        *) Schema name: engineuser
        *) User: engineuser
        *) Password: engineuser

API's exposed:

    *) To get the product list
        GET: http://localhost:8509/priceengine/calculator/products

    *) To get the price list
        GET: http://localhost:8509/priceengine/calculator/prices?product_id={id}
            id: 1 or 2 (1 for penguin-ears, 2 for horseshoe)

    *) To get the final amount
        GET: http://localhost:8509/priceengine/calculator/generate
            This endpoint expects a request body. Sample request body, 
                {"product_id" : 2, "carton_order" : false, "units" : 39}
                

