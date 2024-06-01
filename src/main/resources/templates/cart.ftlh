<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="container mt-5">
        <h2>Корзина</h2>
        <table class="table mt-3">
            <thead>
            <tr>
                <th>Название товара</th>
                <th>Количество</th>
                <th>Цена за единицу</th>
                <th>Общая цена</th>
                <th>Действия</th>
            </tr>
            </thead>
            <tbody>
            <#list cartItems as cartItem>
                <tr>
                    <td>${cartItem.product.title}</td>
                    <td>${cartItem.quantity}</td>
                    <td>${cartItem.product.price} ₽</td>
                    <td>${cartItem.product.price * cartItem.quantity} ₽</td>
                    <td>
                        <form action="/cart/remove" method="post" style="display:inline;">
                            <input type="hidden" name="productId" value="${cartItem.product.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit" class="btn btn-danger btn-sm">Удалить</button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

        <div class="mt-4">
            <form action="/orders/create" method="post">
                <div class="form-group">
                    <label for="pickupPointId">Пункт выдачи</label>
                    <select class="form-control" id="pickupPointId" name="pickupPointId">
                        <#list pickupPoints as pickupPoint>
                            <option value="${pickupPoint.id}">${pickupPoint.name}</option>
                        </#list>
                    </select>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Оформить заказ</button>
            </form>
        </div>
    </div>
</@t.template>