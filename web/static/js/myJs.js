
function pop_init(title, content) {
    //取当前浏览器窗口大小
    var windowWidth = $(document).width();
    var windowHeight = $(document).height();
    //弹窗的大小
    var weight = 280;
    var height = 200;
    $("body").append(
        "<div id='pop_div' style='background:white;display:none;" +
        "position:absolute;" +
        "border:1px solid #b0ffe0;" +
        "width:" + weight + "px;" +
        "height:" + height + "px;" +
        "top:" + (windowHeight - height - 10) + "px;" +
        "left:" + (windowWidth - weight - 10) + "px'>" +
        "<div style='line-height:38px;" +
        "background:#b0ffe0;" +
        "font-size:18px;" +
        "padding:0 0 0 10px;'>" +
        "<div style='float:left;'>" + title + "</div>" +
        "<div style='float:right;cursor:pointer;margin-right: 10px'><b onclick='pop_close()'>X</b></div>" +
        "<div style='clear:both'></div>" +
        "</div>" +
        "<div id='content' style='font-size: 18px;margin-left: 30px'>" +
        content +
        "</div>" +
        "</div>"
    );
}

function pop_close() {
    $('#pop_div').fadeOut(1000);
}

function countDay() {
    $("#day").children().remove();
    var day;
    var year = $(".year:checked").val();
    var month = $(".month:checked").val();
    var flag = isRun(year);
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
        day = 31;
    } else if (month == 2) {
        if (flag) {
            day = 29;
        } else {
            day = 28;
        }
    } else {
        day = 30;
    }
    for (var i = 0; i < day; i++) {
        $("#day").append("<option value=" + (i + 1) + ">" + (i + 1) + "</option>");
    }
}

function isRun(year) {
    return (0 == year % 4 && (year % 100 != 0 || year % 400 == 0));
}

function showPosition() {
    var departmentId = $(".department:checked").val();
    $("#position").children().remove();
    $.ajax({
        type: "get",
        url: "/position/queryByDepartmentId",
        data:{departmentId:departmentId},
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#position").append("<option value=" + data[i].id + ">" + data[i].name + "</option>")
            }
        }
    });
}
