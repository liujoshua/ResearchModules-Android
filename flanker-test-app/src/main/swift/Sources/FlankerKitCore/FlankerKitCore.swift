import java_swift
import Foundation
import AnyCodable
import FlankerKit

public class FlankerKitCore {
  public static func bootstrap(cachePath: String?) {
    setenv("TMPDIR", cachePath!, 1)
    // Required for SSL to work
    setenv("URLSessionCertificateAuthorityInfoFile", cachePath! + "/cacert.pem", 1)

    let persistence = FlankerLocalPersistentData(startDate: .init())
    let engine = FlankerEngine(with: [TestStep()], localPersistentData: persistence)

    NSLog("\(engine)")
  }

  public static func diagnostics() -> String {
    return Bundle.main.bundlePath
  }
}

struct TestStep: FlankerStepProtocol {
  var nextStepIdentifier: String? = nil

  var branchingNavigationRules: [FlankerStepBranchingRule]? = nil

  var stepGroup: FlankerStepGroup? = nil

  var stepBackTo: String? = nil

  let identifier = UUID().uuidString
  var stepName: String? = "Test"
}
