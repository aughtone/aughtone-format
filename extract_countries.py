import json

data = [
  {
    "country": "India",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "China",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "United States",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "Indonesia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Pakistan",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "Nigeria",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Brazil",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Bangladesh",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "Russia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Ethiopia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Mexico",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "Japan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Egypt",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Philippines",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "DR Congo",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Vietnam",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Iran",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Turkey",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Germany",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Thailand",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "United Kingdom",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "Tanzania",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "France",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "South Africa",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "Italy",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Kenya",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Myanmar",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Colombia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "South Korea",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Sudan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Uganda",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Spain",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Algeria",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Iraq",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Argentina",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Afghanistan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Yemen",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Canada",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "Poland",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Morocco",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Angola",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Ukraine",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Uzbekistan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Malaysia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Mozambique",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Ghana",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Peru",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Saudi Arabia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Madagascar",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Ivory Coast",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Nepal",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Cameroon",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Venezuela",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Niger",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Australia",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "North Korea",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Syria",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Mali",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Burkina Faso",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Taiwan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Sri Lanka",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Malawi",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Zambia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Kazakhstan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Chad",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Chile",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Romania",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Somalia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Senegal",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Guatemala",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Netherlands",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Ecuador",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Cambodia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Zimbabwe",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Guinea",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Benin",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Rwanda",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Burundi",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Bolivia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Tunisia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "South Sudan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Haiti",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Belgium",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Jordan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Dominican Republic",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "United Arab Emirates",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Cuba",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Honduras",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Czech Republic",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Sweden",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Tajikistan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Papua New Guinea",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Portugal",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Azerbaijan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Greece",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Hungary",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Togo",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Israel",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Austria",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Belarus",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Switzerland",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Sierra Leone",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Laos",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Turkmenistan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Hong Kong",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Libya",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Kyrgyzstan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Paraguay",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Nicaragua",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Bulgaria",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Serbia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "El Salvador",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Republic of the Congo",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Denmark",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Singapore",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Lebanon",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Finland",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Liberia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Norway",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Slovakia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Palestine",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Central African Republic",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Oman",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Ireland",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "New Zealand",
    "12HourTime": "12 Hour Time"
  },
  {
    "country": "Mauritania",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Costa Rica",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Kuwait",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Panama",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Croatia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Georgia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Eritrea",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Mongolia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Uruguay",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Puerto Rico",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Bosnia and Herzegovina",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Qatar",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Moldova",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Namibia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Armenia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Lithuania",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Jamaica",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Albania",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Gambia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Gabon",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Botswana",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Lesotho",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Guinea-Bissau",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Slovenia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Equatorial Guinea",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Latvia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "North Macedonia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Bahrain",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Trinidad and Tobago",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Timor-Leste",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Estonia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Cyprus",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Mauritius",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Eswatini",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Djibouti",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Fiji",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Reunion",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Comoros",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Guyana",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Solomon Islands",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Bhutan",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Macau",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Luxembourg",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Montenegro",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Suriname",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Western Sahara",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Malta",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Maldives",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Cape Verde",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Brunei",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Belize",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Bahamas",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Iceland",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Guadeloupe",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Martinique",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Vanuatu",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Mayotte",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "French Guiana",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "New Caledonia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Barbados",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "French Polynesia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Sao Tome and Principe",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Samoa",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Curacao",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Saint Lucia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Guam",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Kiribati",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Seychelles",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Grenada",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Micronesia",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Aruba",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Tonga",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Jersey",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Saint Vincent and the Grenadines",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Antigua and Barbuda",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "United States Virgin Islands",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Isle of Man",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Andorra",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Cayman Islands",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Dominica",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Bermuda",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Guernsey",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Greenland",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Faroe Islands",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Saint Kitts and Nevis",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "American Samoa",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Turks and Caicos Islands",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Northern Mariana Islands",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Sint Maarten",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Liechtenstein",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "British Virgin Islands",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Gibraltar",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Monaco",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Marshall Islands",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "San Marino",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Saint Martin",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Palau",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Anguilla",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Cook Islands",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Nauru",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Wallis and Futuna",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Saint Barthelemy",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Tuvalu",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Saint Pierre and Miquelon",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Montserrat",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Falkland Islands",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Tokelau",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Niue",
    "12HourTime": "24 Hour Time"
  },
  {
    "country": "Vatican City",
    "12HourTime": "24 Hour Time"
  }
]

print([d['country'] for d in data if d['12HourTime'] == '12 Hour Time'])
