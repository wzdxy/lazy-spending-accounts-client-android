<template>
    <div id="app">
        <div id="pie" style="width: 100vw;height: 200px;"></div>
        <div id="bar" style="width: 100vw;height: 300px;"></div>
        <!--<div id="line" style="width: 100vw;height: 300px;"></div>-->
    </div>
</template>

<script>
    export default {
        data() {
            return {
                accounts:window.native?[]:[
                    {"id":1,"cost":120,"date":"2018-01-01","time":"08:03","serial":"A"},
                    {"id":2,"cost":64,"date":"2018-01-02","time":"18:03","serial":"B"},
                    {"id":3,"cost":800,"date":"2018-01-02","time":"18:03","serial":"C"},
                    {"id":4,"cost":10,"date":"2018-01-03","time":"18:03","serial":"B"},
                    {"id":5,"cost":65,"date":"2018-01-04","time":"18:03","serial":"E"},
                    {"id":6,"cost":45,"date":"2018-01-04","time":"18:03","serial":"A"},
                    {"id":7,"cost":50,"date":"2018-01-05","time":"18:03","serial":"D"},
                    {"id":8,"cost":500,"date":"2018-01-06","time":"18:03","serial":"C"},
                    {"id":9,"cost":60,"date":"2018-01-07","time":"18:03","serial":"A"},
                ],
                seriesNameArray:[],
                seriesValueArray:[],
                seriesSum:[],
                dateSum:[],
                dateArray:[],
                charts:{
                    pie:null,bar:null,line:null
                }
            }
        },
        methods: {
            getAllAccounts(){
                if(window.native){
                    let res = window.native.getAllAccounts()
                    this.accounts = JSON.parse(res)
                    this.setSeriesData()
                }else{
                    console.log('no native')
                }
            },
            refreshAll(){
                this.charts.pie = this.$echarts.init(document.getElementById('pie'));
                this.charts.pie.setOption({
                    title: {text: 'series'},
                    series: [{
                        name: '销量',
                        type: 'pie',
//                        data: [
//                            {value:220,name:'heh'},
//                            {value:133,name:'dw'},
//                            {value:230,name:'cc'},
//                            {value:240,name:'ccccc'},
//                            {value:126,name:'aaa'},
//                        ]
                    }]
                });
                this.charts.bar = this.$echarts.init(document.getElementById('bar'));
                this.charts.bar.setOption({
                    title: {text: 'series'},
                    xAxis: {
                        data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                    },
                    yAxis: {},
                    series: [{
                        name: '销量',
                        type: 'bar',
                        data: [2,3,4,2,3,45]
                    }]
                });
//                this.charts.line = this.$echarts.init(document.getElementById('line'));
//                this.charts.line.setOption({
//                    title: {
//                        text: '堆叠区域图'
//                    },
//                    tooltip : {
//                        trigger: 'axis',
//                        axisPointer: {
//                            type: 'cross',
//                            label: {
//                                backgroundColor: '#6a7985'
//                            }
//                        }
//                    },
//                    legend: {
//                        data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
//                    },
//                    toolbox: {
//                        feature: {
//                            saveAsImage: {}
//                        }
//                    },
//                    grid: {
//                        left: '3%',
//                        right: '4%',
//                        bottom: '3%',
//                        containLabel: true
//                    },
//                    xAxis : [
//                        {
//                            type : 'category',
//                            boundaryGap : false,
//                            data : ['周一','周二','周三','周四','周五','周六','周日']
//                        }
//                    ],
//                    yAxis : [
//                        {
//                            type : 'value'
//                        }
//                    ],
//                    series : [
//                        {
//                            name:'邮件营销',
//                            type:'line',
//                            stack: '总量',
//                            areaStyle: {normal: {}},
//                            data:[120, 132, 101, 134, 90, 230, 210]
//                        },
//                        {
//                            name:'联盟广告',
//                            type:'line',
//                            stack: '总量',
//                            areaStyle: {normal: {}},
//                            data:[220, 182, 191, 234, 290, 330, 310]
//                        },
//                        {
//                            name:'视频广告',
//                            type:'line',
//                            stack: '总量',
//                            areaStyle: {normal: {}},
//                            data:[150, 232, 201, 154, 190, 330, 410]
//                        },
//                        {
//                            name:'直接访问',
//                            type:'line',
//                            stack: '总量',
//                            areaStyle: {normal: {}},
//                            data:[320, 332, 301, 334, 390, 330, 320]
//                        },
//                        {
//                            name:'搜索引擎',
//                            type:'line',
//                            stack: '总量',
//                            label: {
//                                normal: {
//                                    show: true,
//                                    position: 'top'
//                                }
//                            },
//                            areaStyle: {normal: {}},
//                            data:[820, 932, 901, 934, 1290, 1330, 1320]
//                        }
//                    ]
//                })
            },
            setSeriesData(){
                let tempSum={};
                this.seriesNameArray=[];
                this.seriesValueArray=[];
                this.seriesSum=[];
                this.accounts.forEach((account)=>{
                    if(tempSum.hasOwnProperty(account.serial)){
                        tempSum[account.serial]+=account.cost;
                    }else{
                        tempSum[account.serial]=account.cost;
                    }
                })
                for(let series in tempSum){
                    this.seriesSum.push({
                        name:series,value:tempSum[series]
                    })
                }
                this.seriesSum.sort((a,b)=>b.value-a.value)
                this.seriesSum.forEach((series)=>{
                    this.seriesNameArray.push(series.name)
                    this.seriesValueArray.push(series.value)
                })
//                let today=new Date().getTime();
//                let dataArray = this.accounts.filter((i) => new Date(i.data).getTime() < (today - 3600 * 24 * 1000))
                // 填充数据
                this.charts.pie.setOption({
                    series:{
                        data:this.seriesSum
                    }
                })
//                this.charts.line.setOption({
//                    xAxis:{
//                        data:this.seriesNameArray
//                    },
//                    series:[{
//                        data:this.seriesValueArray
//                    }]
//                })
                this.charts.bar.setOption({
                    xAxis:{
                        data:this.seriesNameArray
                    },
                    series:[{
                        data:this.seriesValueArray
                    }]
                })
            }
        },
        mounted() {
            this.refreshAll()
            this.setSeriesData()
        }
    }
</script>

<style lang="postcss">

</style>
