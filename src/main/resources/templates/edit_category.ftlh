<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="container mt-5">
        <h2>Редактировать категорию</h2>
        <form action="/categories/edit" method="post">
            <input type="hidden" name="id" value="${category.id}">
            <div class="form-group">
                <label for="name">Название</label>
                <input type="text" class="form-control" id="name" name="name" value="${category.name}" required>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Сохранить</button>
        </form>
    </div>
</@t.template>