import Foundation
import java_swift
import JavaCoder
import FlankerKit

public extension FlankerStepProtocol {
  // Decoding SwiftValue type with JavaCoder
  static func from(javaObject: jobject) throws -> FlankerStep {
    // ignore forPackage for basic impl
    return try JavaDecoder(forPackage: AndroidPackage, missingFieldsStrategy: .ignore).decode(FlankerStep.self, from: javaObject)
  }

   // Encoding SwiftValue type with JavaCoder
  func javaObject() throws -> jobject {
    // ignore forPackage for basic impl
    return try JavaEncoder(forPackage: AndroidPackage, missingFieldsStrategy: .ignore).encode(FlankerStep(
        identifier: identifier,
        stepName: stepName,
        nextStepIdentifier: nextStepIdentifier,
        branchingNavigationRules: branchingNavigationRules,
        stepGroup: stepGroup,
        stepBackTo: stepBackTo
      ))
  }
}

//public extension FlankerEngine {
//  public func getNextFlankerStep(step: FlankerStep?) -> FlankerStep? {
//    guard let nextStep =
//  }
//}

public struct FlankerStep: Codable, FlankerStepProtocol {
  public let identifier: String

  public let stepName: String?

  public let nextStepIdentifier: String?

  public let branchingNavigationRules: [FlankerStepBranchingRule]?

  public let stepGroup: FlankerStepGroup?

  public let stepBackTo: String?

  public init(
    identifier: String,
    stepName: String?,
    nextStepIdentifier: String?,
    branchingNavigationRules: [FlankerStepBranchingRule]?,
    stepGroup: FlankerStepGroup?,
    stepBackTo: String?
  ) {
    self.identifier = identifier
    self.stepName = stepName
    self.nextStepIdentifier = nextStepIdentifier
    self.branchingNavigationRules = branchingNavigationRules
    self.stepGroup = stepGroup
    self.stepBackTo = stepBackTo
  }
}
