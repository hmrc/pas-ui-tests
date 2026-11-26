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

package uk.gov.hmrc.ui.specs

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.{AuthWizard, BeforeYouStart, CISAdditionalDetails, CISAppealablePenalties, CISContractorEndDate, CISWhyYouAreAppealing, ExcuseEndDateKnown, FileTheReturnDate, ReasonableExcuseEndDate, SendYourAppeal}
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import org.openqa.selenium.By
import uk.gov.hmrc.selenium.webdriver.Driver.instance

class cisFlowSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("CIS Penalties and Appeals Flow") {

    Scenario("CIS Penalties and Appeals Flow for excuse group 1") {

      Given("the user is logged in through the AuthWizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        "Organisation",
        "HMRC-CIS-ORG",
        "TaxOfficeNumber",
        "100",
        "TaxOfficeReference",
        "AB123"
      )
      Then("the CISAppealablePenalties page is shown")
      CISAppealablePenalties.verifyPageTitle(CISAppealablePenalties.pageTitle)

      When("the user selects a penalty id to begin appealing")
      CISAppealablePenalties.clickPenaltyID()
      Then("the BeforeYouStart page is shown")
      BeforeYouStart.verifyPageTitle(BeforeYouStart.pageTitle)

      When("the user starts the appeal")
      BeforeYouStart.clickStartTheAppealButton()
      Then("the CISWhyYouAreAppealing page is shown")
      CISWhyYouAreAppealing.verifyPageTitle(CISWhyYouAreAppealing.pageTitle)

      When("the user selects none of these as the reason for appealing")
      CISWhyYouAreAppealing.radioButton(CISWhyYouAreAppealing.hmrcOnlineServicesUnavailable)
      CISWhyYouAreAppealing.clickContinueButton()
      Then("the ReasonableExcuseEndDate page is shown")
      ReasonableExcuseEndDate.verifyPageTitle(ReasonableExcuseEndDate.pageTitle)

      When("the user provides their reasonable excuse end date")
      ReasonableExcuseEndDate.enterReasonableExcuseEndDate()
      ReasonableExcuseEndDate.clickContinueButton()
      Then("the CISAdditionalDetails page is shown")
      CISAdditionalDetails.verifyPageTitle(CISAdditionalDetails.pageTitle)

      When("the user provides more details about their reason for appealing")
      CISAdditionalDetails.input(
        By.id(CISAdditionalDetails.reasonForAppeal),
        CISAdditionalDetails.reasonForAppealInput
      )
      CISAdditionalDetails.clickContinueButton()

      Then("the user navigates to Check your answers page")
      SendYourAppeal.verifyPageTitle(SendYourAppeal.pageTitle)
      When("the user accepts and send on CYA")
      SendYourAppeal.clickAcceptAndSend()

      /*

      Then ("the user is navigated to Appeal received")
       */
    }

    Scenario("CIS Penalties and Appeals Flow for excuse group 3 - None of these") {

      Given("the user is logged in through the AuthWizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        "Organisation",
        "HMRC-CIS-ORG",
        "TaxOfficeNumber",
        "100",
        "TaxOfficeReference",
        "AB123"
      )
      Then("the CISAppealablePenalties page is shown")
      CISAppealablePenalties.verifyPageTitle(CISAppealablePenalties.pageTitle)

      When("the user selects a penalty id to begin appealing")
      CISAppealablePenalties.clickPenaltyID()
      Then("the BeforeYouStart page is shown")
      BeforeYouStart.verifyPageTitle(BeforeYouStart.pageTitle)

      When("the user starts the appeal")
      BeforeYouStart.clickStartTheAppealButton()
      Then("the CISWhyYouAreAppealing page is shown")
      CISWhyYouAreAppealing.verifyPageTitle(CISWhyYouAreAppealing.pageTitle)

      When("the user selects none of these as the reason for appealing")
      CISWhyYouAreAppealing.radioButton(CISWhyYouAreAppealing.noneOfThese)
      CISWhyYouAreAppealing.clickContinueButton()
      Then("the user is navigated to Do you know when your reasonable excuse ended page")
      ExcuseEndDateKnown.verifyPageTitle(ExcuseEndDateKnown.pageTitle)

      When("the user confirms they know when their reasonable excuse ended")
      ExcuseEndDateKnown.radioButton(ExcuseEndDateKnown.yes)
      ExcuseEndDateKnown.clickContinueButton()
      Then("the ReasonableExcuseEndDate page is shown")
      ReasonableExcuseEndDate.verifyPageTitle(ReasonableExcuseEndDate.pageTitle)

      When("the user provides their reasonable excuse end date")
      ReasonableExcuseEndDate.enterReasonableExcuseEndDate()
      ReasonableExcuseEndDate.clickContinueButton()
      Then("the CISAdditionalDetails page is shown")
      CISAdditionalDetails.verifyPageTitle(CISAdditionalDetails.pageTitle)

      When("the user provides more details about their reason for appealing")
      CISAdditionalDetails.input(
        By.id(CISAdditionalDetails.reasonForAppeal),
        CISAdditionalDetails.reasonForAppealInput
      )
      CISAdditionalDetails.clickContinueButton()
      Then("the user navigates to Check your answers page")
      SendYourAppeal.verifyPageTitle(SendYourAppeal.pageTitle)
      When("the user accepts and send on CYA")
      SendYourAppeal.clickAcceptAndSend()

      /*
      Then ("the user is navigated to Appeal received")
       */
    }

    Scenario("CIS Penalties and Appeals Flow for excuse group 5 - I am no longer a CIS contractor") {

      Given("the user is logged in through the AuthWizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        "Organisation",
        "HMRC-CIS-ORG",
        "TaxOfficeNumber",
        "100",
        "TaxOfficeReference",
        "AB123"
      )
      Then("the CISAppealablePenalties page is shown")
      CISAppealablePenalties.verifyPageTitle(CISAppealablePenalties.pageTitle)

      When("the user selects a penalty id to begin appealing")
      CISAppealablePenalties.clickPenaltyID()
      Then("the BeforeYouStart page is shown")
      BeforeYouStart.verifyPageTitle(BeforeYouStart.pageTitle)

      When("the user starts the appeal")
      BeforeYouStart.clickStartTheAppealButton()
      Then("the CISWhyYouAreAppealing page is shown")
      CISWhyYouAreAppealing.verifyPageTitle(CISWhyYouAreAppealing.pageTitle)

      When("the user selects no longer as cis contractor as the reason for appealing")
      CISWhyYouAreAppealing.radioButton(CISWhyYouAreAppealing.noLongerCisContractor)
      CISWhyYouAreAppealing.clickContinueButton()
      Then("the user navigates to When did you stop being a CIS contractor?")
      CISContractorEndDate.verifyPageTitle(CISContractorEndDate.pageTitle)

      When("the user enters the date and click continue")
      CISContractorEndDate.enterCISContractorEndDate()
      CISContractorEndDate.clickContinueButton()
      Then("the CISAdditionalDetails page is shown")
      CISAdditionalDetails.verifyPageTitle(CISAdditionalDetails.pageTitle)

      When("the user provides more details about their reason for appealing")
      CISAdditionalDetails.input(
        By.id(CISAdditionalDetails.reasonForAppeal),
        CISAdditionalDetails.reasonForAppealInput
      )
      CISAdditionalDetails.clickContinueButton()
      Then("the user navigates to Check your answers page")
      SendYourAppeal.verifyPageTitle(SendYourAppeal.pageTitle)
      When("the user accepts and send on CYA")
      SendYourAppeal.clickAcceptAndSend()

      /*
        Then ("the user is navigated to Appeal received")
       */
    }

    Scenario("CIS Penalties and Appeals Flow for excuse group 6 - I filed the return on time") {

      Given("the user is logged in through the AuthWizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        "Organisation",
        "HMRC-CIS-ORG",
        "TaxOfficeNumber",
        "100",
        "TaxOfficeReference",
        "AB123"
      )
      Then("the CISAppealablePenalties page is shown")
      CISAppealablePenalties.verifyPageTitle(CISAppealablePenalties.pageTitle)

      When("the user selects a penalty id to begin appealing")
      CISAppealablePenalties.clickPenaltyID()
      Then("the BeforeYouStart page is shown")
      BeforeYouStart.verifyPageTitle(BeforeYouStart.pageTitle)

      When("the user starts the appeal")
      BeforeYouStart.clickStartTheAppealButton()
      Then("the CISWhyYouAreAppealing page is shown")
      CISWhyYouAreAppealing.verifyPageTitle(CISWhyYouAreAppealing.pageTitle)

      When("the user selects filed the return on time as the reason for appealing")
      CISWhyYouAreAppealing.radioButton(CISWhyYouAreAppealing.filedReturnOnTime)
      CISWhyYouAreAppealing.clickContinueButton()
      Then("the user navigates to When did you file the return?")
      FileTheReturnDate.verifyPageTitle(FileTheReturnDate.pageTitle)

      When("the user enters the date and click continue")
      FileTheReturnDate.enterFilingReturnDate()
      FileTheReturnDate.clickContinueButton()
      Then("the CISAdditionalDetails page is shown")
      CISAdditionalDetails.verifyPageTitle(CISAdditionalDetails.pageTitle)

      When("the user provides more details about their reason for appealing")
      CISAdditionalDetails.input(
        By.id(CISAdditionalDetails.reasonForAppeal),
        CISAdditionalDetails.reasonForAppealInput
      )
      CISAdditionalDetails.clickContinueButton()
      Then("the user navigates to Check your answers page")
      SendYourAppeal.verifyPageTitle(SendYourAppeal.pageTitle)
      When("the user accepts and send on CYA")
      SendYourAppeal.clickAcceptAndSend()

      /*
      Then ("the user is navigated to Appeal received")

       */
    }

    Scenario("CIS Penalties and Appeals Flow for excuse group 7 - I am a deemed contractor") {

      Given("the user is logged in through the AuthWizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        "Organisation",
        "HMRC-CIS-ORG",
        "TaxOfficeNumber",
        "100",
        "TaxOfficeReference",
        "AB123"
      )
      Then("the CISAppealablePenalties page is shown")
      CISAppealablePenalties.verifyPageTitle(CISAppealablePenalties.pageTitle)

      When("the user selects a penalty id to begin appealing")
      CISAppealablePenalties.clickPenaltyID()
      Then("the BeforeYouStart page is shown")
      BeforeYouStart.verifyPageTitle(BeforeYouStart.pageTitle)

      When("the user starts the appeal")
      BeforeYouStart.clickStartTheAppealButton()
      Then("the CISWhyYouAreAppealing page is shown")
      CISWhyYouAreAppealing.verifyPageTitle(CISWhyYouAreAppealing.pageTitle)

      When("the user selects i am a deemed contractor as the reason for appealing")
      CISWhyYouAreAppealing.radioButton(CISWhyYouAreAppealing.deemedContractor)
      CISWhyYouAreAppealing.clickContinueButton()

      Then("the user navigates to Check your answers page")
      SendYourAppeal.verifyPageTitle(SendYourAppeal.pageTitle)
      When("the user accepts and send on CYA")
      SendYourAppeal.clickAcceptAndSend()

      /*
      Then ("the user is navigated to Appeal received")
       */
    }
  }
}
