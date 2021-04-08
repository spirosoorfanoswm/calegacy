import {Component, OnInit, Input, OnChanges} from '@angular/core';
import { DataService } from '../../services/data.service';

@Component({
    selector: 'app-risk-graph',
    templateUrl: './risk-graph.component.html',
    styleUrls: ['./risk-graph.component.css']
})
export class RiskGraphComponent implements OnInit, OnChanges {
    @Input() data: any;
    @Input() selectedDate: any;
    @Input() typeChart: string;
    viewData: string;
    labels: any;
    dataLoad: any;

    //  CHART.JS
    public ChartOptions = {
        scaleShowVerticalLines: false,
        responsive: true,
        scales: {
            xAxes: [{
                //stacked: true,
                gridLines: {
                    color: "#fff"
                }, 
                scaleLabel: {
                    display: true,
                    labelString: ''
                }
            }],
            yAxes: [{
                //stacked: true,
                gridLines: {
                    color: "#fff"
                }, 
                scaleLabel: {
                    display: true,
                    labelString: ''
                }
            }]
        }
    };
    public ChartLabels: any;
    public ChartType: string;
    public ChartLegend = true;
    public ChartData = [];

    constructor(private dataService: DataService) {}

    ngOnInit() {

        //  RESET GRAPH
        this.resetGraph();
        //  LOAD GRAPH
        this.runLoadData();
    }

    ngOnChanges() {

        //  RESET GRAPH
        this.resetGraph();
        //  LOAD GRAPH
        this.runLoadData();
        this.ChartOptions = {
            scaleShowVerticalLines: false,
            responsive: true,
            scales: {
                xAxes: [{
                    //stacked: true,
                    gridLines: {
                        color: "#fff"
                    }, 
                    scaleLabel: {
                        display: true,
                        labelString:  this.data.xLabel
                    }
                }],
                yAxes: [{
                    //stacked: true,
                    gridLines: {
                        color: "#fff"
                    }, 
                    scaleLabel: {
                        display: true,
                        labelString:  this.data.yLabel
                    }
                }]
            }
        };

    }

    /**
     * RESET GRAPH
     */
    resetGraph(){

        this.ChartLabels = [];
        this.ChartData   = [];

    }

    /**
     * LOAD DATA TO GRAPHS
     */
    runLoadData() {
       
        this.ChartType = this.typeChart;
        this.ChartLabels = this.data.ChartLabels;
        this.dataLoad = this.data.data[0].data;
       
        

        let dataLabel = this.data.data[0].label[0];

        const getThis = this;

        this.ChartOptions.scales.xAxes[0].scaleLabel.labelString = this.data.xLabel;
        this.ChartOptions.scales.yAxes[0].scaleLabel.labelString = this.data.yLabel;

        Object.keys(this.dataLoad).forEach(function(key) {

           
            const shape = {
                data                : getThis.dataLoad[key],
                label               : dataLabel[key],
                backgroundColor     : (key == '0') ? '#3C94DE' : '#C3DFF5',
                hoverBackgroundColor: (key == '0') ? '#3C94DE' : '#C3DFF5',
                
            };

            getThis.ChartData.push(shape);

        });

    }

}
