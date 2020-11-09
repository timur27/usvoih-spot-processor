package com.usvoih.config;

import com.usvoih.dto.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.usvoih.dto.Category.FOOD;
import static com.usvoih.dto.Day.MONDAY;

public class SpotTestConfiguration {

    public static SpotDetailsDto createTestSpot() {
        SpotDetailsDto spotDetailsDto = new SpotDetailsDto();
        spotDetailsDto.setName("New Spot For Tests");
        spotDetailsDto.setDescription("Spot's Description");
        spotDetailsDto.setAddresses(List.of(createTestAddress()));
        spotDetailsDto.setType(createTestType());
        spotDetailsDto.setContact(createTestContact());
        spotDetailsDto.setCoverPhoto("Test Cover Photo");
        spotDetailsDto.setPhotos("Test Photo 1, Test Photo 2");
        spotDetailsDto.setBusinessHours(List.of(createTestBusinessHours()));
        spotDetailsDto.setRatings(List.of(createTestRating()));

        return spotDetailsDto;
    }

    private static RatingDto createTestRating() {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setDate(LocalDate.now().toString());
        ratingDto.setRating(3.54);
        ratingDto.setReview("Test Rating's review");

        return ratingDto;
    }

    private static AddressDto createTestAddress() {
        AddressDto addressDto = new AddressDto();
        addressDto.setCountry("Test Country");
        addressDto.setCity("Test City");
        addressDto.setStreet("Test Street");
        addressDto.setHouse("Test House Number");
        addressDto.setApartment("Test Apartment Number");
        addressDto.setPostalCode("Test Postal Code");

        return addressDto;
    }

    private static TypeDto createTestType() {
        TypeDto typeDto = new TypeDto();
        typeDto.setCategory(FOOD);
        typeDto.setSubcategory("Test Restaurant");

        return typeDto;
    }

    private static ContactDto createTestContact() {
        ContactDto contactDto = new ContactDto();
        contactDto.setPhone("73627836");
        contactDto.setEmail("test.email@gmail.com");
        contactDto.setWebsite("Test Website");
        contactDto.setInstagram("Test Instagram");
        contactDto.setFacebook("Test Facebook");
        contactDto.setVk("Test VK");

        return contactDto;
    }

    private static BusinessHourDto createTestBusinessHours() {
        BusinessHourDto businessHourDto = new BusinessHourDto();
        businessHourDto.setDay(MONDAY);
        businessHourDto.setOpen(LocalTime.NOON.toString());
        businessHourDto.setClose(LocalTime.MIDNIGHT.toString());

        return businessHourDto;
    }
}
