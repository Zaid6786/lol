import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Dashboard } from '../models/dashboard';
import { environment } from 'src/environments/environment';


@Injectable({
    providedIn: 'root'
})
export class DashboardService {

    private apiUrl = `${environment.apiUrl}/dashboard`;

    constructor(private http: HttpClient) { }

    getDashboardCounts() {
        return this.http.get<Dashboard>(`${this.apiUrl}/counts`);
    }

}