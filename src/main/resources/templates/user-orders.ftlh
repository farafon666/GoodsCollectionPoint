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
                <tr class="<#if order.status == 'PENDING'>status-pending</#if>
                           <#if order.status == 'COMPLETED'>status-completed</#if>
                           <#if order.status == 'CANCELLED'>status-cancelled</#if>">
                    <td>${order.id}</td>
                    <td>${order.pickupPoint.name}</td>
                    <td>${order.totalPrice} ₽</td>
                    <td>
                        <#if order.status == "PENDING">Ожидает</#if>
                        <#if order.status == "COMPLETED">Завершён</#if>
                        <#if order.status == "CANCELLED">Отменён</#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@t.template>