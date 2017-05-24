/**
 * Created by Ivan on 11.04.2017.
 */
const DROP_UP_COMPONENT = '<span class="caret"></span>';

$('.navbar button').click(function searchInAllListener() {
    console.log($('#search').val());

    $.ajax({
        url: `../movie/searchAll?param=${$('#search').val()}`,
        success: function (data) {
            console.log(data);
        }
    })
});

function getPage(number) {
    ACTIVE_PAGE = number;
    findAllMoviesWithAllProperties();

    $('.active').removeClass("active");
    $('.pagination li').eq(number).addClass("active");

}

function round(number) {
    return Math.round(number * 10) / 10;
}

function setUsersRate(data) {

    for (let i = 0; i < data.length; i++) {
        $('.movie-choose').eq(i).html(data[i] + DROP_UP_COMPONENT);
    }
}

function setListenerForChangeRate (movie, user) {
    return `<div class="dropup">
 <button class="btn btn-default dropdown-toggle movie-choose" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">0 ${DROP_UP_COMPONENT}
  </button>
  <ul class="dropdown-menu">
    <li onclick="sendMark('${movie}', 10, '${user}', '#009933')" style="color: #009933;";>10</li>
    <li onclick="sendMark('${movie}', 9, '${user}', '#009933')" style="color: #009933;">9</li>
    <li onclick="sendMark('${movie}', 8, '${user}', '#00ff00')" style="color: #00ff00;">8</li>
    <li onclick="sendMark('${movie}', 7, '${user}', '#99cc00')" style="color: #99cc00;">7</li>
    <li onclick="sendMark('${movie}', 6, '${user}', '#ffff00')" style="color: #ffff00;">6</li>
    <li onclick="sendMark('${movie}', 5, '${user}', '#666633')" style="color: #666633;">5</li>
    <li onclick="sendMark('${movie}', 4, '${user}', '#999966')" style="color: #999966;">4</li>
    <li onclick="sendMark('${movie}', 3, '${user}', '#663300')" style="color: #663300;">3</li>
    <li onclick="sendMark('${movie}', 2, '${user}', '#ff3300')" style="color: #ff3300;">2</li>
    <li onclick="sendMark('${movie}', 1, '${user}', '#ff0000')" style="color: #ff0000;">1</li>
  </ul>
</div>`;
}
