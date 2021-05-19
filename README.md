# web-crawler
web-crawler
URL: http://localhost:8089/webcrawler
HTTP Method: POST

reuest:

{
"url":"http://www.google.com",
"searchText":"google"
}


response:
{
    "url": "http://www.google.com",
    "searchText": "google",
    "links": [
        "https://about.google/intl/en/",
        "http://www.google.com",
        "https://about.google/intl/en/products/#page-content",
        "https://about.google/intl/en/commitments/#content",
        "https://about.google/intl/en/stories/#content",
        "https://www.google.com.sg/imghp?hl=en&ogbl",
        "https://about.google/intl/en/products/",
        "https://www.google.com.sg/intl/en/about/products?tab=ih",
        "https://about.google/intl/en/stories/",
        "https://about.google/intl/en/#content",
        "https://about.google/intl/en/commitments/"
    ],
    "matchedLinks": [
        "- Text: developer.google.com/products (link: https://developers.google.com/products/?hl=en)\n",
        "- Text: developer.google.com/products (link: https://developers.google.com/products/?hl=en)\n",
        "- Text: developer.google.com/products (link: https://developers.google.com/products/?hl=en)\n"
    ]
}
