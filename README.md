## Usvoih ![CircleCI](https://circleci.com/gh/timur27/usvoih-spot-processor.svg?style=svg)

### Project functionalities 
* CRUD operations on spots/extended spots 
* Filtering by
    - categories
    - subcategories
    - approved/unapproved status
* Sorting by
    - map bounds
* Enriching spots with
    - location parameters
    - image url for cover photo stored in AWS S3
* Updating spots with
    - approved/unapproved status
    - business hours
* Validating spots
    
### Phase 2 functionalities 
* Multilingual UI


### Acceptance Criteria
#### New Spot

##### JSON fields AC
* Open and close hours for spot are in 24H format, with '0' at the beginning for 1-digit cases (e.g. 07:00 instead of 7:00)
* Photos - one string, not an array. Argument: no need to create separate table in DB, just maintain the data as one JSON string in one column 
##### JSON (not all fields present)
```sh
{
 "name": "qweq2we2133",
 "description": "Restaurant in the middle of the town",
 "addresses" : [
 	{
 		"country": "Polska",
		"city": "Kraków 30-724"
 	}
 ],
 "type" : {
  "category": "Food", 
  "subcategory": "Restaurant"
 },
 "contact" : {
  "phone": "", 
  "email": "spot_email@yahoo.de",
  "website": "restaurantbyus.uk",
  "instagram": "instagram",≥≤≥
  "facebook": "facebook",
  "vk": "vk"
 },≤
 "coverPhoto" : "sample_bytes_array_to_save",
 "photos": "img.com/img.jpg, img.com/img.jpg, img.com/img.jpg",
 "businessHours" : [
  {
   "day": "Monday",
   "openHour": "07:00",
   "closeHour": "22:00"
  }
 ],
"ratings": [
    {
        "rating": 3.7, 
        "review": "Not bad, but sometimes need to wait more time",
        "date": "2020-11-08"
    }
]
}
``` 

##### Required fields 
```
Name, Country, City, Phone Number
```
##### Unique fields
```
Name, <City, Street, House, Apartment>
```
##### Spot types 
###### Categories 
```
Food
Health
Service
Shops
Entertainments
Business
Education
Beauty
```

###### Subcategories
```
Unique values, provided as raw string by user, and validated by moderator/admin.
```