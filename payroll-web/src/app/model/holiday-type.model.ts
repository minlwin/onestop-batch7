import holidays from '../../assets/model/holiday-type-model.json'

export interface HolidayType {
  code: string
  name: string
}

export const HOLIDAY_TYPES:HolidayType[] = holidays
