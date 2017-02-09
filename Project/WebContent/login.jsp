<%@ include file="common/header.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
			<h1 class="logo">Fabflix</h1>
			<div class="panel panel-default">
				<div class="panel-heading text-center ">
					<h3>
						<Strong>Sign in</Strong>
					</h3>
				</div>
				<div class="panel-body">
					<form action="login" method="post">
						<div class="login input-group input-group-lg">
							<span class="input-group-addon" id="sizing-addon1">Username</span>
							<input name="email" type="text" class="form-control"
								placeholder="Enter your email" aria-describedby="sizing-addon1">
						</div>

						<div class="login input-group input-group-lg">
							<span class="input-group-addon" id="sizing-addon1">Password</span>
							<input name="password" type="password" class="form-control"
								placeholder="Enter your password"
								aria-describedby="sizing-addon1">
						</div>

						<div class="btn-group text-center" role="group">
							<button type="submit" class="btn btn-primary ">Sign in</button>
						</div>
						<p>
							<font color="red">${errMessage}</font>
						</p>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>




