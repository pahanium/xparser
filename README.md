# xparser

This is demo project. Used Spring boot, Spring data, Sring security and other.

Main features is converting Excel file to csv. Your can setup few parser on different Excel price. You can setup witch column and how it should converted to exported csv file. For transformation values of Excel file you can use some function:

* **Trim** - leading and trailing whitespace removed
* **Replace** - replaces each substring of value that matches the given regular expression with the given replacement. For example, replace "," to "."
* **Multiplication** - multiplied by the number. For example, multiplied price by the currency

For some function need specify parameters. If more then one parameters, you must divide them by `;`. For example, _params_ for replace function `,;.`
