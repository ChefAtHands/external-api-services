## Recipe Search Service API Endpoints

The Recipe Search Service runs on **port 8083** and provides the following endpoints:

### Base URL
```
http://localhost:8083
```

### 1. Search Recipes by Ingredients

Search for recipes based on a list of ingredients with optional nutritional filters.

**Endpoint**: `POST /api/recipes/search`

**Request Body**:
```json
{
  "ingredients": ["chicken", "garlic", "onion"],
  "number": 10,
  "offset": 0,
  "minProtein": 30,
  "maxProtein": 100,
  "minCarbs": 10,
  "maxCarbs": 50,
  "minCalories": 300,
  "maxCalories": 600,
  "minFat": 5,
  "maxFat": 30,
  "type": "main course",
  "diet": "vegetarian"
}
```

**Request Parameters**:
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| `ingredients` | array | Yes | List of ingredient names |
| `number` | integer | No | Number of results to return (default: 10) |
| `offset` | integer | No | Offset for pagination (default: 0) |
| `minProtein` | integer | No | Minimum protein in grams |
| `maxProtein` | integer | No | Maximum protein in grams |
| `minCarbs` | integer | No | Minimum carbs in grams |
| `maxCarbs` | integer | No | Maximum carbs in grams |
| `minCalories` | integer | No | Minimum calories |
| `maxCalories` | integer | No | Maximum calories |
| `minFat` | integer | No | Minimum fat in grams |
| `maxFat` | integer | No | Maximum fat in grams |
| `type` | string | No | Recipe type (e.g., "main course", "dessert", "appetizer") |
| `diet` | string | No | Diet type (e.g., "vegetarian", "vegan", "gluten free") |

**Response**:
```json
{
  "results": [
    {
      "id": 715415,
      "title": "Red Lentil Soup with Chicken and Turnips",
      "image": "https://img.spoonacular.com/recipes/715415-312x231.jpg",
      "readyInMinutes": 55,
      "servings": 8,
      "sourceUrl": "https://www.pinkwhen.com/red-lentil-soup-with-chicken-and-turnips/",
      "summary": "Red Lentil Soup with Chicken and Turnips might be a good recipe..."
    }
  ],
  "totalResults": 611,
  "offset": 0,
  "number": 10
}
```

**Example Request**:
```bash
curl -X POST http://localhost:8083/api/recipes/search \
  -H "Content-Type: application/json" \
  -d '{
    "ingredients": ["chicken", "garlic"],
    "number": 5,
    "minProtein": 30
  }'
```

### 2. Get Recipe Details by ID

Retrieve detailed information about a specific recipe, including nutrition facts, ingredients, and instructions.

**Endpoint**: `GET /api/recipes/{id}`

**Path Parameters**:
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| `id` | long | Yes | Spoonacular recipe ID |

**Response**:
```json
{
  "id": 715415,
  "title": "Red Lentil Soup with Chicken and Turnips",
  "image": "https://img.spoonacular.com/recipes/715415-556x370.jpg",
  "readyInMinutes": 55,
  "servings": 8,
  "sourceUrl": "https://www.pinkwhen.com/red-lentil-soup-with-chicken-and-turnips/",
  "summary": "Red Lentil Soup with Chicken and Turnips might be a good recipe...",
  "extendedIngredients": [...],
  "analyzedInstructions": [...],
  "nutrition": {
    "nutrients": [...]
  }
}
```

**Example Request**:
```bash
curl http://localhost:8083/api/recipes/715415
```

---

## Example Usage Scenarios

### Scenario 1: Basic Search
Search for chicken recipes:
```bash
curl -X POST http://localhost:8083/api/recipes/search \
  -H "Content-Type: application/json" \
  -d '{
    "ingredients": ["chicken"],
    "number": 5
  }'
```

### Scenario 2: Search with Nutritional Filters
Find high-protein, low-carb recipes:
```bash
curl -X POST http://localhost:8083/api/recipes/search \
  -H "Content-Type: application/json" \
  -d '{
    "ingredients": ["salmon", "broccoli"],
    "number": 10,
    "minProtein": 30,
    "maxCarbs": 50,
    "minCalories": 300,
    "maxCalories": 600
  }'
```

### Scenario 3: Search with Diet Filter
Find vegetarian pasta recipes:
```bash
curl -X POST http://localhost:8083/api/recipes/search \
  -H "Content-Type: application/json" \
  -d '{
    "ingredients": ["pasta", "tomato", "basil"],
    "number": 10,
    "diet": "vegetarian",
    "type": "main course"
  }'
```

### Scenario 4: Pagination
Get the second page of results:
```bash
curl -X POST http://localhost:8083/api/recipes/search \
  -H "Content-Type: application/json" \
  -d '{
    "ingredients": ["chicken"],
    "number": 10,
    "offset": 10
  }'
```

### Scenario 5: Get Recipe Details
After getting recipe IDs from search, fetch full details:
```bash
curl http://localhost:8083/api/recipes/715415
```

---

## Integration with Other Services

The Recipe Search Service is designed to be called by other microservices in the ChefAtHands ecosystem:

### From Recommendation Service

The recommendation-service calls the recipe-search-service to find recipes based on user ingredients:

```java
// In recommendation-service
RecipeSearchResponse response = recipeSearchClient.searchRecipes(
    userIngredients,  // ["Mozzarella", "Garlic", "Chicken Breast"]
    10,               // number of results
    0,                // offset
    30,               // minProtein
    null,             // maxProtein
    null,             // minCarbs
    50,               // maxCarbs
    null,             // minCalories
    null,             // maxCalories
    null,             // minFat
    null,             // maxFat
    "main course",    // type
    null              // diet
);
```

### Configuration

To integrate with the recipe-search-service, add this to your service's `application.properties`:

```properties
# Recipe Search Service URL
recipe-search-service.url=http://localhost:8083
```

---

## Caching Behavior

- Recipes fetched via the detail endpoint (`GET /api/recipes/{id}`) are automatically cached for 24 hours
- Individual recipes from search results are also cached when retrieved
- Cache reduces API calls to the Spoonacular API (150 calls/day limit on free tier)
- Check cached recipes using `GET /api/recipes/cached`
- Clear cache manually using `DELETE /api/recipes/cached`

---