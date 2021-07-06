```
<!DOCTYPE>
<html>
	<head>
		<style>
		</style>
	</head>
</html>
```

*!DOCTYPE(DOM: Document Object Model)
* html
	* head
		* style (css)
		* title (title of page)
	* body
		* p (pargraph)
		* heading: h1 - h6 (h1 is the largest, h6 is the smallest)
		* div (division)
			* easy to reference
			* divide to break out the page
		* text
		* list
			* list item (li)
			* unordered list(ul)
				* rank by bullet points
			* ordered list(ol)
				* rank by numbers
		* image (img)
			* attributes: src (source), alt (alternative text)
				* sometimes src can't be loaded, so it needs alt for backup
			* width="" (change the size)
		* a (link)
			* href (hyperlink reference)
		* table
			* thead (table head)
				* tr (table row)
					* th (heading)
			* tbody
				* tr (table row)
					* td (table data)
		* call from css
			* id (#)
			* class (.)
			* if there are conflicted
				* order: inline -> id -> class -> type
					* inline (style right inside the tag)
		* form
			* input 
				* type = "text", "password", "submit", "radio"
				* placeholder
				* name (id for this input form)
			* datalist (input has to declare it first)
				* option
					*value=""
		* button
	* tereminal
		* start ./filename (windows)
		* open filename (mac)
		
# CSS
## for each tag can add property
* style
	* color
	* text-align
	* padding (space inside the element) 
	* margin (space outside the element)
	* font-family
		* mulitple fonts unless the first few fonts aren't suppurted
	* font-size
	* font-weight
	* weight
	* height
	* background-color
	* border
		* pixel, solid, color
	* border-collapse
	* selector
		* mulitple element
			* a, b
		* descendant 
			* a b
		* child 
			* a > b
		* adjacent sibling 
			* a + b
		* attribute (a[href=""])
			* [a=b]
		* pseudoclass (:hover)
			* a:b
		* pseudoelement
			* a::b
	* @media (change the format based on the device screen)
		* (min-width)
		* (max-width)
	* display
		* flex (flexbox)
			* flex-wrap 
		* grid (grid)
			* grid-column-gap
			* grid-row-gap
			* grid-template-columns
				* specific pixel or auto
	* bootstrap (getbootstrap.com)
		* elements are done for you
	* scss
		* all variables begin with `$`
			* $color = red;
		* it has to convert from scss to css and run before it compiles
			* command: sass sth.scss:sth.css (run once)
			* command: sass --watch sth.scss:sth.css (run everytime I modify)
			* href in head
		* connect with bootstrap
			* %message{}
			* @extend 
				
		
## declare in html head
* style

## or create a .css file
* link (connect with html)
	* rel="stylesheet
	* href

adding semicolon(;)