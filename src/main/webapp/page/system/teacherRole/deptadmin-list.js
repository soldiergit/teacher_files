layui.use(['form','layer','table','laytpl','util'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        util = layui.util,
        table = layui.table;

    //信息列表
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher_files_war/biz/teacher_findDeptAdmin.action?',//数据接口
        cellMinWidth : 95,
        page: true,//开启分页
        height: "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        id: "infoListTable",
        cols: [[//表头
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'teacherCode', title: '教师工号', minWidth: 100, align: "center"},
            {field: 'teacherName', title: '教师姓名', minWidth: 100, align: 'center'},
            {field: 'entryTime', title: '入职时间', minWidth: 100, align: 'center', templet: function (d) {
                    return util.toDateString(d.entryTime, "yyyy-MM-dd")
                }},
            {field: 'highEdu', title: '最高学历', minWidth: 100, align: 'center'},
            {field: 'firstEdu', title: '第一学历', minWidth: 100, align: 'center'},
            {field: 'roleNames', title: '教师角色', minWidth: 200, align: 'center'},
            {title: '操作', minWidth: 175, templet: '#infoListBar', fixed: "right", align: "center"}
        ]]
    });

    $(".addBtn").click(function () {
        addUser();
    });

    var $ = layui.$, active = {
        reload: function(){
            var dataReload = $('#dataReload');
            //执行重载
            table.reload('infoListTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: dataReload.val()
                }
            }, 'data');
        }
    };
    $('.layui-card-body .reloadBtn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') {//编辑
            addUser(data);
        }
    });

    //添加教师
    function addUser(edit) {
        var index = layui.layer.open({
            title: "添加",
            type: 2,
            content: "deptadmin-add.html",
            maxmin: true,
            area: ['800px', '500px'],
            btn: ['确定', '取消'],
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".Id").val(edit.teacherId);
                    body.find(".teacherCode").val(edit.teacherCode).prop("disabled", true);
                    body.find(".teacherName").val(edit.teacherName);
                    body.find(".deptHide").val(edit.dept.deptId);  //使用中间变量保存部门id
                    body.find(".updateFlag").val(1);
                    form.render();
                }
            },
            yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#addUser");
                submit.click();
            }
        });
        // layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index", index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    };
});