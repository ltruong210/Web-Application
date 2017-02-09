<%@ include file="../common/header.jspf"%>

<p>We use the LIKE predicate in 3 functions inside the Query.java file. </p>
<p>In the first function, browseByTitle(String character, String order, int offset, String sort, String limit), we're passed 
a character which determines what title the user wants to browse by. We use LIKE [character] to find the list of movies 
that start with the specified character.</p>

<p>In the second function,  quickSearch(String pattern, String order, int offset, String sort), 
we're passed a pattern by the user to determine what movie they want to search for. We use LIKE [%pattern%] in order to 
query all the movies that have the pattern in any part of its title.</p>

<p>In the third function, advanceSearch(String title, String year, String director, String firstName, String lastName,
 String order, int offset, String sort), we're passed a number of optional advance search arguments.
  The function will take a combination of all of the arguments and query for any matching movie using LIKE.
   For example, if the user passes in title we will query LIKE [%title%] to find any movies with title in any part of its name.
    If the user also passes in year, we use LIKE [%title%] AND LIKE [%year%] to find any movie that satisfies both queries.</p>
<%@ include file="../common/footer.jspf"%>
