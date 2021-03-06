<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
			<div class="media">
				<div class="media-left media-middle">
					<a href="#"> <img class="media-object"
						src="${movie.banner_url}" width="150" height="200"
						alt="No Movie Image"></a>
				</div>
				<div class="media-body" class="center">
					<ul style="list-style-type:none">
						<li>ID: ${movie.id}
						<li>Title: ${movie.title}</li>
						<li>Year: ${movie.year}</li>
						<li>Director: ${movie.director}</li>
						<li class="horizontal">Genres:</li>
						<c:forEach var="genre" items="${movie.genres}">
							<li class="horizontal"><a href="browsegenre?genre=${genre}">${genre}</a></li>
							<li class="horizontal">,</li>
						</c:forEach>
						<br>
						<li class="horizontal">Stars:</li>
						<c:forEach var="star" items="${movie.stars}">
							<li class="horizontal"><a href="singlestar?id=${star.id}">${star.firstName}
									${star.lastName}</a></li>
							<li class="horizontal">,</li>
						</c:forEach>
						<li>Trailer: <a href="${movie.trailer_url}">${movie.trailer_url}</a></li>
						<li>Price: $5</li>

						<li><a href="cart?title=${movie.title}&id=${movie.id}" class="btn btn-primary">Add to cart</a></li>

					</ul>
				</div>
			</div>
		</div>
	</div>
</div>


<%@ include file="common/footer.jspf"%>




