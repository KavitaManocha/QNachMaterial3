package com.chola.app.data.dto.login

data class Data(
    val api_key: String,
    val category_codes: List<CategoryCode>,
    val category_id: String,
    val contact_email: String,
    val contact_person: String,
    val frequencies: List<Frequency>,
    val frequency: String,
    val location: String,
    val logo: String,
    val mobile: String,
    val module: List<Module>,
    val name: String,
    val org_id: String,
    val org_name: String,
    val phy_frequency: String,
    val sponsor_bank: List<SponsorBank>,
    val token: String,
    val user_email: String,
    val user_id: String,
    val username: String
) {
    override fun toString(): String {
        return "Data(api_key='$api_key', category_codes=$category_codes, category_id='$category_id', contact_email='$contact_email', contact_person='$contact_person', frequencies=$frequencies, frequency='$frequency', location='$location', logo='$logo', mobile='$mobile', module=$module, name='$name', org_id='$org_id', org_name='$org_name', phy_frequency='$phy_frequency', sponsor_bank=$sponsor_bank, token='$token', user_email='$user_email', user_id='$user_id', username='$username')"
    }
}