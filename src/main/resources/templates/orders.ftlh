<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="container mt-5">
        <h2>Все заказы</h2>
        <table class="table mt-3">
            <thead>
            <tr>
                <th>ID заказа</th>
                <th>Пользователь</th>
                <th>Пункт выдачи</th>
                <th>Общая цена</th>
                <th>Статус</th>
                <th>Действия</th>
            </tr>
            </thead>
            <tbody>
            <#list orders as order>
                <tr class="<#if order.status == 'PENDING'>status-pending</#if>
                           <#if order.status == 'COMPLETED'>status-completed</#if>
                           <#if order.status == 'CANCELLED'>status-cancelled</#if>">
                    <td>${order.id}</td>
                    <td>${order.user.username}</td>
                    <td>${order.pickupPoint.name}</td>
                    <td>${order.totalPrice} ₽</td>
                    <td>
                        <#if order.status == "PENDING">Ожидает</#if>
                        <#if order.status == "COMPLETED">Завершён</#if>
                        <#if order.status == "CANCELLED">Отменён</#if>
                    </td>
                    <td>
                        <form action="/orders/updateStatus" method="post" style="display:inline;">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <select name="status" class="form-control">
                                <option value="PENDING"
                                        <#if order.status == "PENDING">selected</#if>>Ожидает</option>
                                <option value="COMPLETED"
                                        <#if order.status == "COMPLETED">selected</#if>>Завершён</option>
                                <option value="CANCELLED"
                                        <#if order.status == "CANCELLED">selected</#if>>Отменён</option>
                            </select>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-primary btn-sm">Обновить</button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@t.template>