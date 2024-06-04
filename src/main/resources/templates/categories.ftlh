<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="container mt-5">
        <h2>Категории</h2>
        <a href="/categories/create" class="btn btn-primary mb-3">Добавить категорию</a>
        <table class="table mt-3">
            <thead>
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Действия</th>
            </tr>
            </thead>
            <tbody>
            <#list categories as category>
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>
                        <a href="/categories/edit/${category.id}" class="btn btn-warning btn-sm">Редактировать</a>
                        <a href="/categories/delete/${category.id}" class="btn btn-danger btn-sm">Удалить</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@t.template>