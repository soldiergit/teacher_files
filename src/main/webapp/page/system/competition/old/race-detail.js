layui.use(['element', 'form', 'table', 'layer', 'laytpl', 'util'], function () {
    var $ = layui.$,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element, //Tab的切换功能，切换事件监听等，需要依赖element模块
        form = layui.form,
        laytpl = layui.laytpl,
        table = layui.table,
        util = layui.util;

    var itemId = $('.itemId').val();
    var tableIns = table.render({
        elem: '#studentInfoList',//数据表格id
        url: '/teacher_files_war/biz/student_findByRace.action?itemId='+itemId,//数据接口
        page : true,//开启分页
        height : "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        id : "infoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {field: 'matchName', title: '赛事名称', minWidth:100, align:"center"},
            {field: 'itemName', title: '作品名称', minWidth:100, align:"center"},
            {field: 'studentCode', title: '学生学号', minWidth:100, align:"center"},
            {field: 'studentName', title: '学生姓名', minWidth:100, align:'center'},
            {field: 'studentPhone', title: '手机号', minWidth:100, align:'center'},
            {field: 'studentEmail', title: '邮箱', minWidth:100, align:'center'},
            {field: 'major', title: '专业名称', minWidth:100, align:'center'},
            {field: 'className', title: '班级名称', minWidth:100, align:'center'},
            {field: 'jobContent', title: '项目分工', minWidth:100, align:'center'},
            {field: 'isPerson', title: '是否负责人', minWidth:100, align:'center', templet:function(d){
                    return d.isPerson=='1'?'是':'否';
                }},
        ]]
    });

});