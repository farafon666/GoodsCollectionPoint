<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="container mt-5">
        <h2>Мои заказы</h2>
        <table class="table mt-3">
            <thead>
            <tr>
                <th>ID заказа</th>
                <th>Пункт выдачи</th>
                <th>Общая цена</th>
                <th>Статус</th>
            </tr>
            </thead>
            <tbody>
            <#list orders as order>
                <tr>
                    <td>${order.id}</td>
                    <td>${order.pickupPoint.name}</td>
                    <td>${order.totalPrice} ₽</td>
                    <td>${order.status}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@t.template>