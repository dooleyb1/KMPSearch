# KMP Search

Java implementation of a Knuth-Morris-Pratt string searching algorithm. 
[Wikipedia](https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm)  Implemented using Sedgewick & Waynes version.

## Usage

To begin with compile all java files.
```
make
```

Search for a **_needle_** in a **_haystack_**.
```
make example
```

Run with the bus services real-time information text file (sourced from [Translink Developer API](https://developer.translink.ca/)).
```
make buses
```

Search for your own _needle_ in a _haystack__.
```
make find
```
## Example

```bash
make find

Enter the pattern you would like to search for:
needle

Enter the string you would like to search for this pattern in:
haystack

Pattern entered = needle
Text entered = haystack

Checking if text 'haystack' contains the pattern 'needle'...

Pattern not found!
```
