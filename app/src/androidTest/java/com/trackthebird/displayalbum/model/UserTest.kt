package com.trackthebird.displayalbum.model

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class UserTest {

    private lateinit var user: User
    private lateinit var address: Address
    private lateinit var geo: Geo
    private lateinit var company: Company

    @Before
    fun setUp() {
        company = Company(
            "harness real-time e-markets",
            "Multi-layered client-server neural-net",
            "Romaguera-Crona"
        )
        geo = Geo(
            "-37.3159",
            "81.1496"
        )
        address = Address(
            "Gwenborough",
            geo,
            "Kulas Light",
            "Apt. 556",
            "92998-3874",
        )
        user = User(
            1,
            "Leanne Graham",
            "Bret",
            "1-770-736-8031 x56442",
            "hildegard.org",
            "Sincere@april.biz",
            address,
            company,
        )
    }

    @Test
    fun getId() {
        assertTrue(user.id == 1)
    }

    @Test
    fun getName() {
        assertTrue(user.name == "Leanne Graham",)
    }

    @Test
    fun getUsername() {
        assertTrue(user.username == "Bret")
    }

    @Test
    fun getPhone() {
        assertTrue(user.phone == "1-770-736-8031 x56442")
    }

    @Test
    fun getWebsite() {
        assertTrue(user.website == "hildegard.org")
    }

    @Test
    fun getEmail() {
        assertTrue(user.email == "Sincere@april.biz")
    }

    @Test
    fun getAddress() {
        assertTrue(user.address == address)
    }

    @Test
    fun getAddressCity() {
        assertTrue(user.address.city == address.city)
    }

    @Test
    fun getAddressStreet() {
        assertTrue(user.address.street == address.street)
    }

    @Test
    fun getAddressSuit() {
        assertTrue(user.address.suite == address.suite)
    }

    @Test
    fun getAddressZipcode() {
        assertTrue(user.address.zipcode == address.zipcode)
    }

    @Test
    fun getAddressGeoLat() {
        assertTrue(user.address.geo.lat == geo.lat)
    }

    @Test
    fun getAddressGeoLng() {
        assertTrue(user.address.geo.lng == geo.lng)
    }

    @Test
    fun getCompany() {
        assertTrue(user.company == company)
    }

    @Test
    fun getCompanyBs() {
        assertTrue(user.company.bs == company.bs)
    }

    @Test
    fun getCompanyCatchPhrase() {
        assertTrue(user.company.catchPhrase == company.catchPhrase)
    }

    @Test
    fun getCompanyName() {
        assertTrue(user.company.name == company.name)
    }

    @Test
    fun testInvalidId() {
        assertFalse(user.id == 0)
    }

    @Test
    fun testInvalidName() {
        assertFalse(user.name == "m",)
    }

    @Test
    fun testInvalidUsername() {
        assertFalse(user.username == "")
    }

    @Test
    fun testInvalidPhone() {
        assertFalse(user.phone == "1-2")
    }

    @Test
    fun testInvalidWebsite() {
        assertFalse(user.website == ".org")
    }

    @Test
    fun testInvalidEmail() {
        assertFalse(user.email == "@april.biz")
    }

    @Test
    fun testInvalidAddress() {
        assertFalse(user.address != address)
    }

    @Test
    fun testInvalidAddressCity() {
        assertFalse(user.address.city == "address")
    }

    @Test
    fun testInvalidAddressStreet() {
        assertFalse(user.address.street == address.city)
    }

    @Test
    fun testInvalidAddressSuit() {
        assertFalse(user.address.suite == address.city)
    }

    @Test
    fun testInvalidAddressZipcode() {
        assertFalse(user.address.zipcode == address.city)
    }

    @Test
    fun testInvalidAddressGeoLat() {
        assertFalse(user.address.geo.lat == geo.lng)
    }

    @Test
    fun testInvalidAddressGeoLng() {
        assertFalse(user.address.geo.lng == geo.lat)
    }

    @Test
    fun testInvalidCompany() {
        assertFalse(user.company != company)
    }

    @Test
    fun testInvalidCompanyBs() {
        assertFalse(user.company.bs == company.catchPhrase)
    }

    @Test
    fun testInvalidCompanyCatchPhrase() {
        assertFalse(user.company.catchPhrase == company.bs)
    }

    @Test
    fun testInvalidCompanyName() {
        assertFalse(user.company.name == company.bs)
    }


}