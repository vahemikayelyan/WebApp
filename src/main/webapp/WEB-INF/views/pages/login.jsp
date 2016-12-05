<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Sign In</div>
				<div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a>
				</div>
			</div>

			<div style="padding-top:30px" class="panel-body">
				<c:if test="${param.error != null}">
					<div id="login-alert" class="alert alert-danger col-sm-12">Invalid username and password.</div>
				</c:if>

				<c:url var="loginUrl" value="/login"/>

				<form class="form-horizontal" action="${loginUrl}" method="post">
					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username"
							   required>
					</div>

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						<input type="password" class="form-control" id="password" name="password"
							   placeholder="Enter Password" required>
					</div>

					<div class="input-group">
						<div class="checkbox">
							<label>
								<input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
							</label>
						</div>
					</div>

					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

					<div style="margin-top:10px" class="form-group">
						<!-- Button -->
						<div class="col-sm-12 controls">
							<input type="submit" class="btn btn-success" value="Log in">
							<a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-12 control">
							<div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
								Don't have an account!
								<a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">Sign Up Here</a>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>