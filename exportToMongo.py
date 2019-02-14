import csv, pymongo

def getConnection():
  return pymongo.MongoClient(DB_HOST,
  username="mapfood",
  password="mapfoodsquad6",
  authSource="mapfood",
  authMechanism="SCRAM-SHA-1")

def readCSV(file_path):
  rows = []
  with open(file_path, "r") as file:
    reader = csv.DictReader(file)
    title = reader.fieldnames
    for row in reader:
      rows.extend([{title[i]:row[title[i]] for i in range(len(title))}])
  return rows

def renameIdColumn(column_name, in_list, useCounter=False):
  if useCounter:
    count = 1
  for item in in_list:
    if column_name in item:
      if useCounter:
        item.pop(column_name)
        item["_id"] = count
        count += 1
      else:
        item["_id"] = int(item.pop(column_name).replace(",", ""))

def convertCoordinatesIntoGeoJSON(in_list, isLowercase=False):
  longitude = "Longitude"
  latitude = "Latitude"
  if isLowercase:
    longitude = longitude.lower()
    latitude = latitude.lower()
  for item in in_list:
    item["loc"] = {
      "type": "Point",
      "coordinates": [float(item.pop(latitude)), float(item.pop(longitude))]
    }

def dropCollection(collection):
  DB[collection].drop()

def insertAllIntoCollection(collection, in_list):
  dropCollection(collection)
  DB[collection].insert_many(in_list, ordered=True)

def importFromCSV(file_path, collection_name,
                  id_column="", replaceId=True, hasCoordinates=True):
  my_list = readCSV(file_path)
  if replaceId:
    renameIdColumn(id_column, my_list)
  if hasCoordinates:
    convertCoordinatesIntoGeoJSON(my_list)
  insertAllIntoCollection(collection_name, my_list)

def removeProductColumns(product):
  if product["restaurant"]:
    product.pop("restaurant")
  if product["address_city"]:
    product.pop("address_city")
  if product["restaurant_id"]:
    product.pop("restaurant_id")

def addProductsToRestaurants(restaurants, products):
  restaurant_ids = {}
  count = 0
  for restaurant in restaurants:
    restaurant["products"] = []
    restaurant_ids[restaurant["restaurant_id"].upper()] = restaurant
  for product in products:
    this_restaurant_id = product["restaurant_id"].upper()
    try:
      restaurant_ids[this_restaurant_id]["products"].append(product)
      removeProductColumns(product)
    except:
      pass

def keepPortoAlegreRestaurantsWithProducts(restaurants):
  new = []
  i = 0
  while i < len(restaurants):
    restaurant = restaurants[i]
    if len(restaurant["products"]) > 0:
      if restaurant["address_city"] == "PORTO ALEGRE":
        new.append(restaurant)
    i += 1
  return new

DB_HOST = "mongodb://ds157509.mlab.com:57509"
DB = getConnection().mapfood

CSV_PATH = "./src/main/resources/static/"
CLIENT_CSV = CSV_PATH + "clientes.csv"
MOTOBOY_CSV = CSV_PATH + "motoboys.csv"
RESTAURANT_CSV = CSV_PATH + "estabelecimento-por-municipio.csv"
PRODUCTS_CSV = CSV_PATH + "produtos-por-estabelecimento.csv"

importFromCSV(CLIENT_CSV, "client", "ID Cliente")
importFromCSV(MOTOBOY_CSV, "motoboy", "ID Motoboy")

restaurants = readCSV(RESTAURANT_CSV)
products = readCSV(PRODUCTS_CSV)
renameIdColumn("item_id", products, True)
convertCoordinatesIntoGeoJSON(restaurants, True)
addProductsToRestaurants(restaurants, products)
restaurants = keepPortoAlegreRestaurantsWithProducts(restaurants)
renameIdColumn("restaurant_id", restaurants, True)
insertAllIntoCollection("restaurant", restaurants)
