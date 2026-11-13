/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object ReasonableExcuseEndDate extends BasePage {

  override def pageUrl: String = "cis-paye-penalties-and-appeals/reasonable-excuse-end-date"

  override def pageTitle: String =
    "When did your reasonable excuse end? - Penalties and Appeals - GOV.UK"

  val day: String = "value.day"

  val dayInput: String = "28"

  val month: String = "value.month"

  val monthInput: String = "10"

  val year: String = "value.year"

  val yearInput: String = "2026"

  def enterReasonableExcuseEndDate(): Unit = inputDateMonthAndYear(
    By.id(day),
    By.id(month),
    By.id(year),
    dayInput,
    monthInput,
    yearInput
  )
}
