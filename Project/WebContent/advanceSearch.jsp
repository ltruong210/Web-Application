<%@ include file="common/header.jspf"%>

<%@ include file="common/navbar.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Advance Search</h3>
				</div>
				<div class="panel-body">
					<form action="advancesearch" method="get">
						<div class="login input-group input-group-lg">
							<span class="input-group-addon" id="sizing-addon1">Title</span> <input
								name="title" type="text" class="form-control"
								placeholder="Movie Title" aria-describedby="sizing-addon1">
						</div>

						<div class="login input-group input-group-lg">
							<span class="input-group-addon" id="sizing-addon1">Director</span>
							<input name="director" type="text" class="form-control"
								placeholder="Director" aria-describedby="sizing-addon1">
						</div>

						<div class="login input-group input-group-lg">
							<span class="input-group-addon" id="sizing-addon1">Year</span> <input
								name="year" type="text" class="form-control" placeholder="Year"
								aria-describedby="sizing-addon1">
						</div>

						<div class="login input-group input-group-lg">
							<span class="input-group-addon" id="sizing-addon1">Star's
								first name</span> <input name="firstName" type="text"
								class="form-control" placeholder="Star's first name"
								aria-describedby="sizing-addon1">
						</div>

						<div class="login input-group input-group-lg">
							<span class="input-group-addon" id="sizing-addon1">Star's
								last name</span> <input name="lastName" type="text"
								class="form-control" placeholder="Star's last name"
								aria-describedby="sizing-addon1">
						</div>
						<div class="btn-group text-center" role="group">
							<button type="submit" class="btn btn-primary ">Search</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="common/footer.jspf"%>