import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-college-logo',
  templateUrl: './college-logo.component.html',
  styleUrls: ['./college-logo.component.css']
})
export class CollegeLogoComponent implements OnInit {

  selectedFile!: File;

  logoPreview: string | null = null;

  constructor(

    private http: HttpClient,
    private toastr: ToastrService

  ) { }

  ngOnInit(): void {

    this.loadLogo();

  }

  loadLogo(): void {

    this.http.get<any>(`${environment.apiUrl}/settings/get`)

      .subscribe({

        next: (res) => {

          if (res.logoUrl) {

            this.logoPreview =
              `${environment.apiUrl}` +
              res.logoUrl +
              '?t=' +
              new Date().getTime();

          }

        },

        error: (err) => {

          console.error(err);

        }

      });

  }

  onFileSelected(event: any): void {

    if (event.target.files.length > 0) {

      this.selectedFile = event.target.files[0];

      const reader = new FileReader();

      reader.onload = (e: any) => {

        this.logoPreview = e.target.result;

      };

      reader.readAsDataURL(this.selectedFile);

    }

  }

  uploadLogo(): void {

    if (!this.selectedFile) {

      this.toastr.warning(
        'Please select a logo first.',
        'Warning'
      );

      return;

    }

    const formData = new FormData();

    formData.append('file', this.selectedFile);

    this.http.post<any>(
      `${environment.apiUrl}/settings/upload-logo`,
      formData
    )

      .subscribe({

        next: (res) => {

          this.logoPreview =
            `${environment.apiUrl}` +
            res.logoUrl +
            '?t=' +
            new Date().getTime();

          this.toastr.success(
            'College logo uploaded successfully.',
            'Success'
          );

        },

        error: (err) => {

          console.error(err);

          this.toastr.error(
            'Unable to upload logo.',
            'Error'
          );

        }

      });

  }

}