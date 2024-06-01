<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Поиск товаров</h1><hr>
    <form method="get" action="/">
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="searchPickupPoint">Пункт выдачи</label>
                <select id="searchPickupPoint" class="form-control">
                    <option value="">Пункт выдачи не выбран</option>
                    <#list points as point>
                        <option value="${point.id}">${point.name} - ${point.address}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group col-md-10">
                <label for="searchWord">Поиск по объявлениям</label>
                <input type="text" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control"
                       id="searchWord">
            </div>
            <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
        </div>
    </form>
    <hr>
    <div class="card-columns">
        <#list products as product>
            <div class="card text-white bg-dark my-3 product">
                <div class="card-top">
                    <form action="/cart/add" method="post">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <div class="form-group">
                            <label for="quantity-${product.id}">Количество</label>
                            <input type="number" class="form-control" id="quantity-${product.id}" name="quantity" value="1" min="1">
                        </div>
                        <button type="submit" class="btn btn-primary">Добавить в корзину</button>
                    </form>
                </div>
                <img src="/images/${product.previewImageId}"
                     class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title">${product.title}</h5>
                    <p class="card-text">${product.price} ₽</p>
                    <a href="/product/${product.id}" class="product-link">Информация</a>
                </div>
                <div class="card-footer">
                    ${product.pickupPoint.name}
                </div>
            </div>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>