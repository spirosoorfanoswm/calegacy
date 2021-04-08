import {Injectable, isDevMode} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class NotificationService{
    display = 'none';


    showError(message: string): void {
        // The second parameter is the text in the button. 
        // In the third, we send in the css class for the snack bar.
        alert(message);
      }

      openModal() {
        this.display = 'block';
      }
    
      onCloseHandled() {
        this.display = 'none';
      }

}