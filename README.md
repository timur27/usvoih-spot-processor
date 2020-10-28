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
##### JSON
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
  "instagram": "instagram",
  "facebook": "facebook",
  "vk": "vk"
 },
 "coverPhoto" : "sample_bytes_array_to_save",
 "photos": [
	"img.com/img.jpg",
	"img.com/img.jpg",
	"img.com/img.jpg"
 ]
 "businessHours" : [
  {
   "day": "Monday",
   "openHour": "7:00AM",
   "closeHour": "6:00PM"
  }
 ],
 "tags": [
	"coffee",
	"atmosphere",
	"loft"
 ]
}
``` 

##### Required fields 
```
Name, Country, City, Phone Number
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