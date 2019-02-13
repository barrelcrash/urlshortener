# URL Shortening Service

A URL shortening service in the style of services like TinyURL.

## How It Works

When URLs are submitted to the service, it is internally assigned a numerical ID. That ID also undergoes a [radix](https://en.wikipedia.org/wiki/Radix) conversion to base 52 numeral system whose characters are `a-zA-Z`. This yields a short ID (e.g. `aabXiv`) which is passed back to the user as a short url, who can then pass it back to the service to retrieve or be redirected to their original URL.

## Endpoints

* `/short/shortenURL`
  * Shortens a URL to a six-character string.
* `/short/get/[shortURL]
  * Retrieves the value of the original URL that `shortURL` represents.
* `/short/[shortURL]` (Pending)
  * Redirects the user to the original URL that `shortURL` represents.
