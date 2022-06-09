---
title: aw05 v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.5"

---

# aw05

> v1.0.0

# products

<a id="opIdlistProducts"></a>

## GET List all products

GET /products

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|A paged array of products|[Products](#schemaproducts)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|unexpected error|[Error](#schemaerror)|

# product

<a id="opIdshowProductById"></a>

## GET Info for a specific product

GET /products/{productId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|productId|path|string| 是 |The id of the product to retrieve|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Expected response to a valid request|[Product](#schemaproduct)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|unexpected error|[Error](#schemaerror)|

# cart

<a id="opIdlistCart"></a>

## GET List all products in cart

GET /cart

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|A paged array of products in carts|[Cart](#schemacart)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|unexpected error|[Error](#schemaerror)|

<a id="opIdaddProduct"></a>

## POST Add one product to Cart

POST /cart/{productId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|productId|path|string| 是 |The id of the product to add to Cart|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|A paged array of products in carts after add one product|[Cart](#schemacart)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|unexpected error|[Error](#schemaerror)|

<a id="opIddeleteProduct"></a>

## DELETE delete one product in Cart

DELETE /cart/{productId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|productId|path|string| 是 |The id of the product to delete in Cart|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|A paged array of products in carts after delete one product|[Cart](#schemacart)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|unexpected error|[Error](#schemaerror)|

<a id="opIdmodifyProduct"></a>

## PUT Modify one product's number in Cart

PUT /cart/{productId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|productId|path|string| 是 |The id of the product to modify in Cart|
|productNum|query|string| 是 |The new number of the product to modify in Cart|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|A paged array of products in carts after add one product|[Cart](#schemacart)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|unexpected error|[Error](#schemaerror)|

## GET session

GET /session

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

<a id="opIdcheckout"></a>

## GET check out product in Cart

GET /checkout

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|A paged array of products in carts after check out|[Cart](#schemacart)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|unexpected error|[Error](#schemaerror)|

# delivery

<a id="opIdlistInfoByUserID"></a>

## GET List delivery info in system

GET /delivery/{userId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userId|path|string| 是 |User id to view delivery information|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|A paged array of delivery info|[Orders](#schemaorders)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|unexpected error|[Error](#schemaerror)|

# 数据模型

<h2 id="tocS_Product">Product</h2>

<a id="schemaproduct"></a>
<a id="schema_Product"></a>
<a id="tocSproduct"></a>
<a id="tocsproduct"></a>

```json
{
  "id": "string",
  "name": "string",
  "price": null,
  "image": "string"
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|id|string|true|none|none|
|name|string|true|none|none|
|price|double|false|none|none|
|image|string|false|none|none|

<h2 id="tocS_Item">Item</h2>

<a id="schemaitem"></a>
<a id="schema_Item"></a>
<a id="tocSitem"></a>
<a id="tocsitem"></a>

```json
{
  "quantity": 0,
  "product": {
    "id": "string",
    "name": "string",
    "price": null,
    "image": "string"
  }
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|quantity|integer(int32)|true|none|none|
|product|[Product](#schemaproduct)|true|none|none|

<h2 id="tocS_Cart">Cart</h2>

<a id="schemacart"></a>
<a id="schema_Cart"></a>
<a id="tocScart"></a>
<a id="tocscart"></a>

```json
[
  {
    "quantity": 0,
    "product": {
      "id": "string",
      "name": "string",
      "price": null,
      "image": "string"
    }
  }
]

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[Item](#schemaitem)]|false|none|none|

<h2 id="tocS_Error">Error</h2>

<a id="schemaerror"></a>
<a id="schema_Error"></a>
<a id="tocSerror"></a>
<a id="tocserror"></a>

```json
{
  "code": 0,
  "message": "string"
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|code|integer(int32)|true|none|none|
|message|string|true|none|none|

<h2 id="tocS_Products">Products</h2>

<a id="schemaproducts"></a>
<a id="schema_Products"></a>
<a id="tocSproducts"></a>
<a id="tocsproducts"></a>

```json
[
  {
    "id": "string",
    "name": "string",
    "price": null,
    "image": "string"
  }
]

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[Product](#schemaproduct)]|false|none|none|

<h2 id="tocS_Order">Order</h2>

<a id="schemaorder"></a>
<a id="schema_Order"></a>
<a id="tocSorder"></a>
<a id="tocsorder"></a>

```json
{
  "cart": [
    {
      "quantity": 0,
      "product": {
        "id": "string",
        "name": "string",
        "price": null,
        "image": "string"
      }
    }
  ],
  "orderTotalPrice": 0,
  "userID": "string",
  "deliveryState": "string",
  "deliveryID": "string"
}

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|cart|[Cart](#schemacart)|true|none|none|
|orderTotalPrice|number(double)|true|none|none|
|userID|string|true|none|none|
|deliveryState|string|true|none|none|
|deliveryID|string|true|none|none|

<h2 id="tocS_Orders">Orders</h2>

<a id="schemaorders"></a>
<a id="schema_Orders"></a>
<a id="tocSorders"></a>
<a id="tocsorders"></a>

```json
[
  {
    "cart": [
      {
        "quantity": 0,
        "product": {
          "id": "string",
          "name": "string",
          "price": null,
          "image": "string"
        }
      }
    ],
    "orderTotalPrice": 0,
    "userID": "string",
    "deliveryState": "string",
    "deliveryID": "string"
  }
]

```

### 属性

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|*anonymous*|[[Order](#schemaorder)]|false|none|none|

