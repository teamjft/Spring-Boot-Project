<fieldset>
<div class="form-group">
<form:input path="name" class="form-control" placeholder="Name" type="text">
    </div>
    <div class="form-group">
    <form:input class="form-control" placeholder="isbn" path="isbn" >
        </div>
        <div class="form-group">
        <form:input class="form-control" placeholder="Edition" path="edition" >
            </div>
            <div class="form-group">
            <form:input class="form-control" placeholder="Description" path="description">
                </div>
                <div class="form-group">
                <form:input class="form-control" placeholder="E-mail" path="email">
                    </div>
                    <div class="form-group">
                    <form:input class="form-control" placeholder="Price" path="price" >
                        </div>
                        <div class="form-group">
                        <form:input class="form-control" placeholder="publisher" path="publisher" >
                            </div>
                            <div class="form-group">
                            <form:input class="form-control" placeholder="Author Name" path="authorName" >
                                </div>
                                <div class="form-group">
                                <form:input class="form-control" placeholder="Publication Year" path="publicationYear">
                                    </div>
                                    <select class="form-control" multiple>
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category.id}">${category.name}
                                    </c:forEach>
                                    </select>

                                    <div class="form-group">
                                    <form:input class="form-control" path="totalNumberOfCopies" type="number">
                                    </div>

                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Add Book">

                                    </fieldset>
