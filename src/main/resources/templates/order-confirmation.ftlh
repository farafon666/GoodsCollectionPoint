<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="container mt-5">
        <h2>Заказ #${order.id} успешно создан!</h2>
        <div class="mt-4">
            <p><strong>Пункт выдачи:</strong> ${order.pickupPoint.name}</p>
            <p><strong>Статус заказа:</strong>  <#if order.status == "PENDING">Ожидает</#if>
                                                <#if order.status == "COMPLETED">Завершён</#if>
                                                <#if order.status == "CANCELLED">Отменён</#if></p>
        </div>
        <table class="table mt-3">
            <thead>
            <tr>
                <th>Название товара</th>
                <th>Количество</th>
                <th>Цена за единицу</th>
                <th>Общая цена</th>
            </tr>
            </thead>
            <tbody>
            <#list order.orderDetails as orderDetail>
                <tr>
                    <td>${orderDetail.product.title}</td>
                    <td>${orderDetail.quantity}</td>
                    <td>${orderDetail.price} ₽</td>
                    <td>${orderDetail.price * orderDetail.quantity} ₽</td>
                </tr>
            </#list>
            </tbody>
        </table>

        <div class="mt-4">
            <p><strong>Итоговая сумма:</strong> ${order.totalPrice} ₽</p>
        </div>
    </div>
</@t.template>