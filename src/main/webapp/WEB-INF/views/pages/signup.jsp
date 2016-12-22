<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <form:form method="POST" modelAttribute="user" class="form-horizontal">
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="ssoId">SSO ID</label>

                        <div class="col-md-7">
                            <c:choose>
                                <c:when test="${edit}">
                                    <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm"
                                                disabled="true"/>
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="ssoId" class="help-inline"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="email">Email</label>

                        <div class="col-md-7">
                            <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="email" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="password">Password</label>

                        <div class="col-md-7">
                            <form:input type="password" path="password" id="password" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="password" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="passwordConfirm">Re Password</label>

                        <div class="col-md-7">
                            <form:input type="password" path="passwordConfirm" id="passwordConfirm"
                                        class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="passwordConfirm" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div style="margin-top:10px" class="form-group">
                    <!-- Button -->
                    <div class="col-sm-12 controls">
                        <input type="submit" class="btn btn-success" value="Sign up">
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>