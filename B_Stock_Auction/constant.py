login_URLS=[
    "https://bstock.com/bestbuy/customer/account/login/",
    "https://bstock.com/lowes/customer/account/login/",
    "https://bstock.com/almo/customer/account/login/",
    "https://bstock.com/costcoappliances/auction/auction/list/"
]
inventory_URLS = [
    "https://bstock.com/bestbuy/auction/auction/view/id/",
    "https://bstock.com/lowes/auction/auction/view/id/",
    "https://bstock.com/almo/auction/auction/view/id/ ",
    "https://bstock.com/costcoappliances/auction/auction/view/id/"
]
manifest = ""
file_path_1 = "https://m.bstock.com/m/downloads/get?site=srs&sku="
file_path_2 = "&file_type=csv"
file_path = ""

states = {
    'CA' : ['CA', 'NV', 'UT', 'AZ'],
    'WA' : ['WA', 'OR', 'ID', 'MT'],
    'TX' : ['CO', 'NMa', 'TX', 'KS','OK','AR','LA'],
    'IL' : ['MN', 'IA', 'NE', 'MO', 'WI', 'IL', 'MI', 'IN', 'KY', 'OH'],
    'GA' : ['MS', 'TN', 'AL', 'GA', 'SC', 'NC'],
    'FL' : ['FL'],
    'NJ' : ['ME', 'VT', 'NH', 'MA', 'CT', 'RI', 'NY', 'NJ', 'PA', 'MD', 'DE', 'WV', 'VA']
}

titles = [
        "id",
        "date",
        "type",
        "bid_count", 
        "number_of_bidders",
        "value_price",
        "current_price",
        "percentage",
        "product_condition",
        "units",
        "city",
        "warehouse",
        "item_location_state",
        "item_location_country"]