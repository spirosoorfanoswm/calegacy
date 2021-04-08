import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'sortEngine'
})
export class SortPipe implements PipeTransform {

    transform(items: any[], params: any ): any[]  {
      

        let paramsCol = params;
        if (!items) {
         return  null;
        }

        if (!params) {
         return items;
        }

        if(typeof items[0][params[0].key] === "boolean" ) {
            items.sort((a,b) =>params[0].order? 
            b[params[0].key] - a[params[0].key]:
            a[params[0].key] - b[params[0].key]);
        } else if(!isNaN(items[0][params[0].key])) {
          
                items.sort((a,b) =>params[0].order? 
                Number(b[params[0].key]) -   Number(a[params[0].key]):
                    Number(a[params[0].key]) -   Number(b[params[0].key]));
        } else {
            items.sort((a,b) =>params[0].order? 
            b[params[0].key].localeCompare(a[params[0].key]) : 
            a[params[0].key].localeCompare(b[params[0].key]));
        }
        
        return items;
       
    }
}
