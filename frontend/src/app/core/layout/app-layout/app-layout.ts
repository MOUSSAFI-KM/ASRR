import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-app-layout',
  imports: [
    RouterLink,//RouterLink : Allows navigation
    RouterLinkActive,//Allows us to highlight the current page
    RouterOutlet //this is where the selected page will appear
  ],
  templateUrl: './app-layout.html',
  styleUrl: './app-layout.css',
})
export class AppLayout {

}
