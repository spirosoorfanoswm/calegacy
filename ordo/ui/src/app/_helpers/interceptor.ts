import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {Observable, throwError, } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { NotificationService } from './../services/notification.service';
import { AlertService } from '../_alert';

@Injectable()
export class Interceptor implements HttpInterceptor {
    constructor(private notificationService: NotificationService,
                private alertService: AlertService){}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add authorization header with jwt token if available

        request = request.clone({
            setHeaders: {
                'x-access-token' : '',
                'Access-Control-Allow-Headers' : '*',
                'Access-Control-Allow-Origin' : '*'
            }
        });

        //return next.handle(request);

        return next.handle(request).pipe(
            catchError(error => {
             if(error.status === 0 ) {
                window.location.reload();
             }
              else {
                
                if(error.error === undefined || error.error.message === undefined ) {
                    this.alertService.error('Action could not be fullfiled');
                    //this.notificationService.showError(error.error.message);
                } else {
                    this.alertService.error(error.error.message);
                    //this.notificationService.showError('Action could not be fullfiled');
                }   
                return throwError(error);
              }
            
            })
          );
    }
}