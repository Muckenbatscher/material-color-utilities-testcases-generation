// Copyright 2026 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import 'package:material_color_utilities/energy/energy_colors.dart';
import 'package:material_color_utilities/hct/hct.dart';
import 'package:test/test.dart';

void main() {
  test('energyColorsWithHueValues_returnsEnergyColors', () {
    final result = energyColorsWithHueValues(
      version: EnergyVersion.v1_1_0,
      Hct.fromInt(0xFF0000FF),
      false,
      0.0,
      0.0,
    );
    expect(result.energyComplementMid, isNull);
    expect(result.energyMid, isNotNull);
    expect(result.energyEnd, isNotNull);
  });

  test('energyColorsWithHueValues_toneBelow20', () {
    final baseColor = Hct.from(260.0, 20.0, 10.0);
    final result = energyColorsWithHueValues(
      baseColor,
      false,
      100.0,
      200.0,
      version: EnergyVersion.v1_1_0,
    );

    expect(result.energyEnd.tone, closeTo(58.0, 5.0));
    expect(result.energyEnd.hue, closeTo(100.0, 5.0));
  });

  test('energyColorsWithHueValues_toneBetween20And90', () {
    final baseColor = Hct.from(260.0, 20.0, 50.0);
    final result = energyColorsWithHueValues(
      baseColor,
      false,
      100.0,
      200.0,
      version: EnergyVersion.v1_1_0,
    );

    expect(result.energyEnd.tone, closeTo(90.0, 5.0));
    expect(result.energyEnd.hue, closeTo(100.0, 5.0));
  });

  test('energyColorsWithHueValues_toneAbove90', () {
    final baseColor = Hct.from(260.0, 20.0, 95.0);
    final result = energyColorsWithHueValues(
      baseColor,
      false,
      100.0,
      200.0,
      version: EnergyVersion.v1_1_0,
    );

    expect(result.energyEnd.tone, greaterThan(80.0));
    expect(result.energyMid.hue, closeTo(200.0, 5.0));
  });

  test('energyColorsWithHueRotations', () {
    final baseColor = Hct.from(100.0, 50.0, 50.0);
    final result = energyColorsWithHueRotations(
      version: EnergyVersion.v1_1_0,
      baseColor,
      false,
      1.0,
      20.0,
      10.0,
    );

    expect(result.energyMid.hue, closeTo(110.0, 0.5));
    expect(result.energyEnd.hue, closeTo(120.0, 0.5));
  });

  test('energyColorsWithHueRotations_reverse', () {
    final baseColor = Hct.from(100.0, 50.0, 50.0);
    final result = energyColorsWithHueRotations(
      version: EnergyVersion.v1_1_0,
      baseColor,
      false,
      -1.0,
      20.0,
      10.0,
    );

    expect(result.energyMid.hue, closeTo(90.0, 0.5));
    expect(result.energyEnd.hue, closeTo(80.0, 0.5));
  });

  test('energyColorsWithBaseColorRole_primarySecondary', () {
    final baseColor = Hct.from(0.0, 50.0, 50.0);
    final primaryColor = Hct.from(0.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(60.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 87.0),
      isBaseline: false,
      baseColorRole: BaseColorRole.primary,
    );

    expect(result.energyMid.hue, closeTo(15.27, 0.5));
    expect(result.energyEnd.hue, closeTo(31.39, 0.5));
    expect(result.energyComplementMid, isNull);
  });

  test('energyColorsWithBaseColorRole_tertiary', () {
    final baseColor = Hct.from(0.0, 50.0, 50.0);
    final primaryColor = Hct.from(60.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(0.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 87.0),
      isBaseline: false,
      baseColorRole: BaseColorRole.tertiary,
    );

    expect(result.energyMid.hue, closeTo(15.27, 0.5));
    expect(result.energyEnd.hue, closeTo(31.39, 0.5));
    expect(result.energyComplementMid, isNull);
  });

  test('energyColorsWithBaseColorRole_surface', () {
    final baseColor = Hct.from(0.0, 50.0, 50.0);
    final primaryColor = Hct.from(120.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(240.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 87.0),
      isBaseline: false,
      baseColorRole: BaseColorRole.surface,
    );

    expect(result.energyMid.hue, closeTo(131.25, 0.5));
    expect(result.energyEnd.hue, closeTo(135.0, 0.5));
    expect(result.energyComplementMid, isNotNull);
  });

  test('energyColorsWithBaseColorRole_toneBelow20_withTertiaryChroma', () {
    final baseColor = Hct.from(100.0, 2.0, 10.0);
    final primaryColor = Hct.from(100.0, 2.0, 10.0);
    final tertiaryColor = Hct.from(140.0, 20.0, 10.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 87.0),
      isBaseline: false,
      baseColorRole: BaseColorRole.primary,
    );

    expect(result.energyEnd.chroma, lessThan(3.0));
  });

  test('energyColorsWithBaseColorRole_toneBelow20_withHighTertiaryChroma', () {
    final baseColor = Hct.from(100.0, 2.0, 10.0);
    final primaryColor = Hct.from(100.0, 2.0, 10.0);
    final tertiaryColor = Hct.from(140.0, 30.0, 10.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 87.0),
      isBaseline: false,
      baseColorRole: BaseColorRole.primary,
    );

    expect(result.energyEnd.chroma, lessThan(3.0));
  });

  test('energyColorsWithBaseColorRole_primarySecondary_baseline', () {
    final baseColor = Hct.from(0.0, 50.0, 50.0);
    final primaryColor = Hct.from(0.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(60.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 87.0),
      isBaseline: true,
      baseColorRole: BaseColorRole.primary,
    );

    expect(result.energyMid.hue, closeTo(14.9, 1.0));
    expect(result.energyEnd.hue, closeTo(269.4, 1.0));
  });

  test('energyColorsWithBaseColorRole_tertiary_baseline', () {
    final baseColor = Hct.from(0.0, 50.0, 50.0);
    final primaryColor = Hct.from(60.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(0.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 87.0),
      isBaseline: true,
      baseColorRole: BaseColorRole.tertiary,
    );

    expect(result.energyMid.hue, closeTo(17.6, 1.0));
    expect(result.energyEnd.hue, closeTo(69.1, 1.0));
  });

  test('energyColorsWithBaseColorRole_surface_baseline', () {
    final baseColor = Hct.from(0.0, 50.0, 50.0);
    final primaryColor = Hct.from(120.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(240.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 87.0),
      isBaseline: true,
      baseColorRole: BaseColorRole.surface,
    );

    expect(result.energyMid.hue, closeTo(239.1, 1.0));
    expect(result.energyEnd.hue, closeTo(259.8, 1.0));
  });

  test('energyColorsWithAccents_inferred_primarySecondary', () {
    final baseColor = Hct.from(0.0, 50.0, 50.0);
    final primaryColor = Hct.from(0.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(60.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 98.0),
      isBaseline: false,
    );

    expect(result.energyMid.hue, closeTo(15.0, 1.0));
    expect(result.energyEnd.hue, closeTo(31.5, 1.0));
  });

  test('energyColorsWithAccents_inferred_tertiary', () {
    final baseColor = Hct.from(0.0, 50.0, 50.0);
    final primaryColor = Hct.from(60.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(0.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 98.0),
      isBaseline: false,
    );

    expect(result.energyMid.hue, closeTo(15.0, 1.0));
    expect(result.energyEnd.hue, closeTo(31.5, 1.0));
  });

  test('energyColorsWithAccents_inferred_surface', () {
    final baseColor = Hct.from(0.0, 2.0, 98.0);
    final primaryColor = Hct.from(120.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(240.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 10.0, 98.0),
      isBaseline: false,
    );

    expect(result.energyMid.hue, closeTo(131.5, 0.5));
    expect(result.energyEnd.hue, closeTo(135.0, 0.5));
  });

  test('energyColorsWithAccents_surface_primaryDirectionFlip', () {
    final baseColor = Hct.from(0.0, 2.0, 98.0);
    final primaryColor = Hct.from(0.0, 50.0, 50.0);
    final secondaryColor = Hct.from(15.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(350.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      secondaryColor,
      tertiaryColor,
      Hct.from(0.0, 10.0, 98.0),
      isBaseline: false,
    );

    expect(result.energyMid.hue, closeTo(348.76, 0.5));
    expect(result.energyEnd.hue, closeTo(345.03, 0.5));
  });

  test('energyColorsWithAccents_accentPrimaryTertiary', () {
    final baseColor = Hct.from(45.0, 50.0, 50.0);
    final primaryColor = Hct.from(0.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(60.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 98.0),
      isBaseline: false,
    );

    expect(result.energyMid.hue, closeTo(57.33, 0.5));
    expect(result.energyEnd.hue, closeTo(71.08, 0.5));
  });

  test('energyColorsWithAccents_accentNarrowSector', () {
    final baseColor = Hct.from(0.0, 50.0, 50.0);
    final primaryColor = Hct.from(0.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(10.0, 50.0, 50.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      Hct.from(0.0, 0.0, 98.0),
      isBaseline: false,
    );

    expect(result.energyMid.hue, closeTo(15.0, 0.5));
    expect(result.energyEnd.hue, closeTo(31.5, 0.5));
  });

  test('energyColorsWithAccents_accentNearSurface', () {
    final baseColor = Hct.from(50.0, 15.0, 60.0);
    final primaryColor = Hct.from(0.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(60.0, 50.0, 50.0);
    final surfaceColor = Hct.from(0.0, 0.0, 98.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      surfaceColor,
      isBaseline: false,
    );

    expect(result.energyMid.hue, closeTo(37.5, 0.5));
    expect(result.energyEnd.hue, closeTo(24.7, 1.0));
  });

  test('testWrappers', () {
    final base = 0xFF0000FF; // Blue
    final primary = 0xFFFF0000; // Red
    final secondary = 0xFF00FF00; // Green
    final tertiary = 0xFFFFFF00; // Yellow
    final surface = 0xFFFFFFFF; // White

    // withAccents
    final result1 = energyColorsIntWithAccents(
      base,
      primary,
      secondary,
      tertiary,
      surface,
      isBaseline: true,
    );
    expect(result1.energyEnd, isNot(equals(0)));

    // withHueRotations
    final result2 = energyColorsIntWithHueRotations(
      base,
      false,
      1.0,
      30.0,
      15.0,
    );
    expect(result2.energyEnd, isNot(equals(0)));

    // withHueValues
    final result3 = energyColorsIntWithHueValues(base, false, 30.0, 15.0);
    expect(result3.energyEnd, isNot(equals(0)));
  });

  test('energyColorsWithAccents_surfaceLikeToneEdgeCases', () {
    final primary = Hct.from(257.0, 48.0, 40.0);
    final tertiary = Hct.from(100.0, 24.0, 60.0);
    final surface = Hct.from(257.0, 4.0, 1.0); // dark, tone=1
    final base = Hct.from(257.0, 1.0, 5.0); // chroma=1 < 2.5*4=10. tone=5.

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      base,
      primary,
      primary,
      tertiary,
      surface,
      isBaseline: false,
    );

    expect(result.energyMid.hue, closeTo(243.0, 0.5));
    expect(result.energyEnd.hue, closeTo(240.5, 0.5));
  });

  test('isTertiaryLike_whenBaseIsCloseToTertiary', () {
    final baseColor = Hct.from(60.0, 50.0, 50.0);
    final primaryColor = Hct.from(120.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(65.0, 50.0, 50.0);
    final surfaceColor = Hct.from(0.0, 0.0, 98.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      surfaceColor,
      isBaseline: false,
    );

    expect(result.energyMid.hue, closeTo(73.19, 0.1));
    expect(result.energyEnd.hue, closeTo(86.8, 0.1));
  });

  test('isTertiaryLike_whenBaseIsNotCloseToTertiary', () {
    final baseColor = Hct.from(90.0, 50.0, 50.0);
    final primaryColor = Hct.from(120.0, 50.0, 50.0);
    final tertiaryColor = Hct.from(65.0, 50.0, 50.0);
    final surfaceColor = Hct.from(0.0, 0.0, 98.0);

    final result = energyColorsWithAccents(
      version: EnergyVersion.v1_1_0,
      baseColor,
      primaryColor,
      primaryColor,
      tertiaryColor,
      surfaceColor,
      isBaseline: false,
    );

    expect(result.energyMid.hue, closeTo(78.03, 0.1));
    expect(result.energyEnd.hue, closeTo(64.21, 0.1));
  });
}
