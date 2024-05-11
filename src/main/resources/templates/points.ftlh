<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Пункты выдачи</h1>
    <hr>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Добавить новый пункт выдачи
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/points/create" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="pointName">Наименование пункта</label>
                <input type="text" required class="form-control"
                       id="pointName" name="name">
            </div>
            <div class="form-group">
                <label for="pointAddress">Адрес пункта</label>
                <input type="text" required class="form-control"
                       id="pointAddress" name="address">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </form>
    </div>
    <div class="card-columns">
        <#list points as point>
            <div class="card text-white bg-dark my-3 product">
                <div class="card-top">
                    <form action="/points/delete/${point.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-danger m-3">
                            Удалить
                        </button>
                    </form>
                </div>
                <div class="card-body">
                    <h5 class="card-title">${point.name}</h5>
                </div>
                <div class="card-footer">
                    ${point.address}
                </div>
            </div>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>