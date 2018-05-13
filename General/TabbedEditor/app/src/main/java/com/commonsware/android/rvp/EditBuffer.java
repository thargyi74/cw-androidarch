/***
 Copyright (c) 2016-2018 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 Covered in detail in the book _Android's Architecture Components_
 https://commonsware.com/AndroidArch
 */

package com.commonsware.android.rvp;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;

class EditBuffer implements Parcelable {
  private Editable prose;
  final private String title;

  EditBuffer(String title) {
    this(title, new SpannableStringBuilder());
  }

  EditBuffer(String title, Editable prose) {
    this.prose=prose;
    this.title=title;
  }

  private EditBuffer(Parcel in) {
    prose=new SpannableStringBuilder(in.readString());
    title=in.readString();
  }

  @Override
  public String toString() {
    return(title);
  }

  Editable getProse() {
    return(prose);
  }

  void setProse(Editable prose) {
    this.prose=prose;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(prose.toString());
    dest.writeString(title);
  }

  @SuppressWarnings("unused")
  public static final Parcelable.Creator<EditBuffer> CREATOR=
    new Parcelable.Creator<EditBuffer>() {
    @Override
    public EditBuffer createFromParcel(Parcel in) {
      return(new EditBuffer(in));
    }

    @Override
    public EditBuffer[] newArray(int size) {
      return(new EditBuffer[size]);
    }
  };
}
