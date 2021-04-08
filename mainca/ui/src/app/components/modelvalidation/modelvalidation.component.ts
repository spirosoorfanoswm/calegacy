import {Component, OnInit, OnDestroy, isDevMode, ViewChild} from '@angular/core';
import { Subscription } from 'rxjs';
import {DataService} from '../../services/data.service';
import {GeneralService} from '../../services/general.service';
import {Router, ActivatedRoute} from '@angular/router';
@Component({
  selector: 'app-scenarios',
  templateUrl: './modelvalidation.component.html',
  styleUrls: ['./modelvalidation.component.css']
})
export class ModelvalidationComponent implements OnInit, OnDestroy {

   sub1: Subscription;
    sub4: Subscription;
    data: any;
    context: any;
    params: any;
    urlParams = {};
    constructor(private router: Router,
      private route: ActivatedRoute,
      private dataService: DataService, 
      private generalService: GeneralService) {
    }

    ngOnInit() {
      this.initSubscriptions();
      this.urlChange();

    }

    ngOnDestroy() {
      this.sub1.unsubscribe();
      this.sub4.unsubscribe();
    }

    initSubscriptions() {
      this.sub4 = this.dataService.currentContext.subscribe(context => {

        this.context = context;

        if (this.params) {
            this.fireData(this.params);
        }
    });
        
    }

    urlChange() {
      this.route.paramMap.subscribe(params => {
          this.params = params;
          this.fireData(params);
      });
  }

    fireData(params) {
      this.sub1 = this.dataService.currentContext.subscribe(context => {
        this.context = context;
        this.urlParams = {
          contextId     : this.context
        };
        this.generalService.getAvailableModels(this.urlParams).subscribe(data => {
          this.data = data.modelValidationRules;         

      });
    });
    }
    

    
    

   

    
    


   
}
